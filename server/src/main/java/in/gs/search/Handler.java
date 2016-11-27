package in.gs.search;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import in.gs.utils.Constants;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Handler implements RequestHandler<Request, ItemCollection> {

    AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    DynamoDB dynamoDB;

    public static void main(String[] args) {
        Handler handler = new Handler();
        Request r = new Request();
//        r.setResource(Constants.AVAILABLE_RES);
        r.setAge("2");
        r.setPlayerid("2039840918");
//        for(int i=0; i<25; i++)
        handler.handleRequest(r, null);

    }

    @Override
    public ItemCollection handleRequest(Request request, Context context) {

        Response r = new Response();
//        client = new AmazonDynamoDBClient(
//                new ProfileCredentialsProvider());
        client.withEndpoint("dynamodb.us-east-2.amazonaws.com");
        dynamoDB = new DynamoDB(client);

        if (request.getResource() != null && request.getResource().contains(Constants.AVAILABLE_RES)) {
            makeResourceAvailable(request);
        } else {
            return searchOpponents();
        }

        return null;
    }

    private void makeResourceAvailable(Request request) {

        Table table = dynamoDB.getTable(Constants.TABLE_AVAILABILITY);
        Item item = new Item().withPrimaryKey("age", request.getAge()).withNumber("last_available_time", System.currentTimeMillis()).withString("player", request.getPlayerid());
        table.putItem(item);
    }

    private ItemCollection searchOpponents() {

        Table table = dynamoDB.getTable(Constants.TABLE_AVAILABILITY);

        ItemCollection<QueryOutcome> collection;
        Object b = null;
        while (true) {
            QuerySpec querySpec = new QuerySpec();
            querySpec.withMaxResultSize(Constants.TABLE_AVAILABILITY_READ_LIMIT).withScanIndexForward(false)
                    .withHashKey(Constants.TABLE_AVAILABILITY_PRIMARY_KEY, "2").withConsistentRead(true);
            if (b != null) {
                RangeKeyCondition range = new RangeKeyCondition(Constants.TABLE_AVAILABILITY_SORT_KEY).lt(b);
                querySpec.withRangeKeyCondition(range);
            }
            collection = table.query(querySpec);
//            if(collection.getAccumulatedItemCount() == 0)
//                break;
            for (Item q : collection) {
                b = q.get(Constants.TABLE_AVAILABILITY_SORT_KEY);
            }
            break;
        }
//         table.getItem("age", "1");
        return collection;
    }
}
