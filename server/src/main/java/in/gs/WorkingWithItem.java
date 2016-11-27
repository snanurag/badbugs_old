package in.gs;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by ashrinag on 11/18/2016.
 */
public class WorkingWithItem {

    public ResponseClass putItem( RequestClass request, Context context){

//        DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
//                new ProfileCredentialsProvider()));
//        DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
//                new EnvironmentVariableCredentialsProvider()));

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.withEndpoint("dynamodb.us-east-2.amazonaws.com");

        DynamoDB dynamoDB = new DynamoDB(client);

//        ListTablesResult list = client.listTables();
//        System.out.println(list);
       Table table = dynamoDB.getTable("Players");


        Item item = new Item()
                .withPrimaryKey("id", "206"+System.currentTimeMillis())
                .withString("Title", "20-Bicycle 206")
                .withString("Description", "206 description")
                .withString("BicycleType", "Hybrid")
                .withString("Brand", "Brand-Company C")
                .withNumber("Price", 500)
                .withStringSet("Color",  new HashSet<String>(Arrays.asList("Red", "Black")))
                .withString("ProductCategory", "Bike")
                .withBoolean("InStock", true)
                .withNull("QuantityOnHand");
//
    // Write the item to the table
        PutItemOutcome outcome = table.putItem(item);
//        Map<String, AttributeValue> map = new HashMap();
//        map.put("id", new AttributeValue(String.valueOf(System.currentTimeMillis())));
//        map.put("age", new AttributeValue(request.getAge()));
//        map.put("firstName", new AttributeValue(request.getFirstName()));
//        PutItemResult result =  client.putItem("Players",map);
        ResponseClass r = new ResponseClass();
//        if(result!= null) r.setGreetings("true");
//        else r.setGreetings("false");
        return r;
    }

    public static void main(String[] args) {
        WorkingWithItem w = new WorkingWithItem();
        w.putItem(new RequestClass(), null);
    }
}
