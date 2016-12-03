package in.gs.search;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import in.gs.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Handler implements RequestHandler<Request, Response> {

    //TODO check if only one attribute of all items can be read.
    //TODO define read_limit of Players table.
    //TODO Collect all the player ids from searched opponents
    //TODO Read Players from player tables for searched opponents player ids.
    //TODO Create a batch delete of all the busy players + the returning opponent.

    private AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    private DynamoDB dynamoDB;
    private volatile Object last_searched_sort_key = null;

    @Override
    public Response handleRequest(Request request, Context context) {


        Response r = new Response();
//        client = new AmazonDynamoDBClient(
//                new ProfileCredentialsProvider());
        client.withEndpoint("dynamodb.us-east-2.amazonaws.com");
        dynamoDB = new DynamoDB(client);

        if (request.getResource() != null && request.getResource().contains(Constants.MAKE_AVAILABLE)) {
            makeResourceAvailable(request);
        } else {
            long time = System.currentTimeMillis();

            String id = null;
            while(id == null && System.currentTimeMillis() - time < 5000) id = searchOpponents(request);
            r.setPlayerid(id);
        }

        return r;
    }

    private void makeResourceAvailable(Request request) {

        Table table = dynamoDB.getTable(Constants.TABLE_AVAILABILITY.tableName());
        Item item = new Item().withPrimaryKey(Constants.TABLE_AVAILABILITY.PRIMARY_KEY.value(), request.getAge())
                .withNumber(Constants.TABLE_AVAILABILITY.SORT_KEY.value(), System.currentTimeMillis()).withString(Constants.TABLE_AVAILABILITY.PLAYER_ID.value(),
                        request.getPlayerid());
        table.putItem(item);
    }

    private String searchOpponents(Request request) {

        Table table = dynamoDB.getTable(Constants.TABLE_AVAILABILITY.tableName());

        ItemCollection<QueryOutcome> collection;

        do{
            QuerySpec querySpec = new QuerySpec();

            querySpec.withMaxResultSize(Constants.TABLE_AVAILABILITY.readLimit()).withScanIndexForward(false)
                    .withHashKey(Constants.TABLE_AVAILABILITY.PRIMARY_KEY.value(), request.getAge()).withConsistentRead(true);
            if (last_searched_sort_key != null) {
                RangeKeyCondition range = new RangeKeyCondition(Constants.TABLE_AVAILABILITY.SORT_KEY.value()).lt(last_searched_sort_key);
                querySpec.withRangeKeyCondition(range);
            }

            collection = table.query(querySpec);
            List<String> player_ids = new ArrayList<>();
            for (Item q : collection) {
                last_searched_sort_key = q.get(Constants.TABLE_AVAILABILITY.SORT_KEY.value());
                player_ids.add(q.get(Constants.TABLE_AVAILABILITY.PLAYER_ID.value()).toString());
            }

            List<String> idsToDelete = getIdsToDelete(player_ids);

            for(String id:player_ids){
                if(idsToDelete== null) idsToDelete = new ArrayList<>();
                if(!idsToDelete.contains(id)){
                    idsToDelete.add(id);
                    deleteBusyPlayers(collection, idsToDelete);
                    return id;
                }
            }

        } while(collection.iterator().hasNext());

        last_searched_sort_key = null;

        return null;
    }

    private void deleteBusyPlayers(ItemCollection<QueryOutcome> collection, List<String> ids) {
        if (ids != null && ids.size() != 0) {
            TableWriteItems availabilityTable = new TableWriteItems(Constants.TABLE_AVAILABILITY.tableName());
            int count = 0;
            for (Item item : collection) {
                if (ids.contains(item.get(Constants.TABLE_AVAILABILITY.PLAYER_ID.value()))) {
                    availabilityTable.addHashAndRangePrimaryKeyToDelete(Constants.TABLE_AVAILABILITY.PRIMARY_KEY.value(),
                            item.get(Constants.TABLE_AVAILABILITY.PRIMARY_KEY.value()), Constants.TABLE_AVAILABILITY.SORT_KEY.value(),
                            item.get(Constants.TABLE_AVAILABILITY.SORT_KEY.value()));
                    count++;
                    if(count == 25){
                        batchWriteOperation(availabilityTable);
                        count = 0;
                    }
                }
            }
            batchWriteOperation(availabilityTable);
        }
    }

    private List<String> getIdsToDelete(List<String> ids) {

        if (ids.isEmpty()) {
            return null;
        }

        List<String> idsToDelete = new ArrayList<>();
        TableKeysAndAttributes tableKeysAndAttributes = new TableKeysAndAttributes(Constants.TABLE_PLAYERS.tableName());
        tableKeysAndAttributes.addHashOnlyPrimaryKeys("id", ids.toArray());

        BatchGetItemOutcome outcome = dynamoDB.batchGetItem(tableKeysAndAttributes);
        Map<String, KeysAndAttributes> unprocessed;
        do {
            unprocessed = outcome.getUnprocessedKeys();
            List<Item> items = outcome.getTableItems().get(Constants.TABLE_PLAYERS.tableName());
            for (Item item : items) {
                if (item.getNumber("busy").intValue() == 1) {
                    idsToDelete.add(item.get("id").toString());
                }
            }
            if(!unprocessed.isEmpty()) outcome = dynamoDB.batchGetItemUnprocessed(unprocessed);
        } while (!unprocessed.isEmpty());

        return idsToDelete;
    }

    private void batchWriteOperation(TableWriteItems... tables){

        List<TableWriteItems> list = new ArrayList<>();
        for(TableWriteItems t: tables){
            if(t.getItemsToPut()!= null && t.getItemsToPut().size()> 0) list.add(t);
        }
        TableWriteItems[] array = new TableWriteItems[list.size()];

        if(list.size()> 0){
            BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(list.toArray(array));
            Map<String, List<WriteRequest>> unprocessed;
            do{
                unprocessed = outcome.getUnprocessedItems();
                if(!unprocessed.isEmpty()) outcome = dynamoDB.batchWriteItemUnprocessed(unprocessed);
            }while(!unprocessed.isEmpty());
        }
    }

    public static void main(String[] args) {
        Handler h = new Handler();
    //    h.insertTestDataToMakeAvailable();
        h.testSearchOpponent();
    }

    private void testInsertDataToMakeAvailable(){
        Request r = new Request();
        r.setResource(Constants.MAKE_AVAILABLE);
        r.setAge(2);
        Integer playerId = 1000000000;
        for(int i=0; i< 100; i++){
            r.setPlayerid((++playerId).toString());
            handleRequest(r, null);
        }
    }

    private void testSearchOpponent(){
        Request r = new Request();
        r.setAge(2);
        String id;
        while(true){
            id = handleRequest(r,null).getPlayerid();
            System.out.println(id);
        }

    }

}
