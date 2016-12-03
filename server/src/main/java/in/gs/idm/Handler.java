package in.gs.idm;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import in.gs.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashrinag on 12/2/2016.
 */
public class Handler implements RequestHandler<Request, ItemCollection> {
    AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    DynamoDB dynamoDB;


    @Override
    public ItemCollection handleRequest(Request request, Context context) {

//        client = new AmazonDynamoDBClient(
//                new ProfileCredentialsProvider());
        client.withEndpoint("dynamodb.us-east-2.amazonaws.com");
        dynamoDB = new DynamoDB(client);
        insertEntries(request);

        return null;
    }

    private void insertEntries(Request request) {
        Table table = dynamoDB.getTable(Constants.TABLE_PLAYERS.tableName());
        Item item = new Item().withPrimaryKey("id", request.getId()).withNumber("busy", request.getBusy()).withMap("powers", request.getPowers())
                .withNumber("age", request.getAge()).withNumber("totalbugs", request.getTotalBugs());
        table.putItem(item);

    }

    public static void main(String[] args) {
        Handler h = new Handler();
        h.testInsertEntries();
    }

    private void testInsertEntries() {

        Integer playerId = 1000000000;
        for (int i = 0; i < 100; i++) {
            Request r = new Request();
            r.setId((++playerId).toString());
            r.setAge(2);
            if(i%2 == 1) r.setBusy(1);
            r.setTotalBugs((int)(10000*Math.random()));
            Map<String, Integer> powers = new HashMap<>();
            r.setPowers(powers);
            powers.put(Request.POWER.SILVER_SPELL.type, (int)(100*Math.random()));
            powers.put(Request.POWER.GOLD_SPELL.type, (int)(100*Math.random()));
            powers.put(Request.POWER.GUM.type, (int)(100*Math.random()));
            powers.put(Request.POWER.TORCH.type, (int)(100*Math.random()));
            powers.put(Request.POWER.BURNING_THREAD.type, (int)(100*Math.random()));
            powers.put(Request.POWER.NET.type, (int)(100*Math.random()));
            powers.put(Request.POWER.BURNING_NET.type, (int)(100*Math.random()));
            powers.put(Request.POWER.BUG_TRAP.type, (int)(100*Math.random()));
            powers.put(Request.POWER.BOMB.type, (int)(100*Math.random()));
            handleRequest(r, null);
        }
    }
}
