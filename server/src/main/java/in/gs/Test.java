package in.gs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import in.gs.gameplay.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 11/17/2016.
 */
public class Test implements RequestHandler<RequestClass, ResponseClass> {
    @Override
    public ResponseClass handleRequest(RequestClass requestClass, Context context) {



        ResponseClass r = new ResponseClass();
        r.setGreetings("he he he he ha ha ha!!!");
        return r;
    }

    public Response myHandler(){

        return null;
    }
}
