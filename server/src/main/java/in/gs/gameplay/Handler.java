package in.gs.gameplay;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Handler implements RequestHandler<Request, Response> {

    @Override
    public Response handleRequest(Request input, Context context) {
        Response r = new Response();

        Response.Rally rally1 = r.new Rally();
        Response.Rally rally2 = r.new Rally();

        rally1.setTime("123123123");
        Response.Bug bug1 = r.new Bug();
        Response.Bug bug2 = r.new Bug();

        bug1.setAngle(12);
        bug1.setCorner(1);
        bug1.setLevel(3);
        bug1.setType(2);

        bug2.setAngle(29);
        bug2.setCorner(2);
        bug2.setLevel(1);
        bug2.setType(5);

        List<Response.Bug> bugs = new ArrayList<>();
        bugs.add(bug1);
        bugs.add(bug2);

        rally1.setRally(bugs);

        rally2.setTime("432324234");

        List<Response.Rally> rallies = new ArrayList<>();

        rallies.add(rally1);
        rallies.add(rally2);

        r.setRallies(rallies);

        return r;
    }

}
