package in.gs.gameplay;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import in.gs.utils.BugGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Handler implements RequestHandler<Request, Response> {

    //TODO on getting request from player, it stores bugmissed and send back the holding generated bug list back and transfer same to the dynamodb.
    //TODO From the bugmissed number it calculates the next generation and keep it on hold.
    //TODO when request of another player comes in, it checks the rally requested. If rally is generated then fetches from db and return. If rally is in process of generation then either sends back the same rally or harder, depending on bugsmissed.

    Map<String, Response> gameAndNextResponseMap = new HashMap<>();

    @Override
    public Response handleRequest(Request input, Context context) {
        String gameId = input.getGameId();
        synchronized (gameId){
            if(gameAndNextResponseMap.get(gameId) != null){
                Response r = gameAndNextResponseMap.get(gameId);
                float lastRallyTime = r.getRallies().get(0).getTime();
                if(lastRallyTime == input.getNextRallyTime()){
                    if(input.getBugsmissed() > 0 && !r.isHardnessIncreased()){
                        r = BugGenerator.getNextRallies(r.getHardness(), true);
                        gameAndNextResponseMap.put(gameId, r);
                    }
                }
                else if(lastRallyTime < input.getNextRallyTime()){
                    if(input.getBugsmissed() > 0){
                        r = BugGenerator.getNextRallies(r.getHardness(), true);

                        return gameAndNextResponseMap.put(gameId, r);
                    }
                    gameAndNextResponseMap.put(gameId, r);
                }
            }
            else {

            }
        }
        return gameAndNextResponseMap.get(gameId);
    }

    private void checkAndReplaceResponse(Request input, Response r){

    }

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "abcd";
        new T(s1).start();
        new T(s2).start();

    }

    static class T extends Thread{
        String s;
        T(String s){
            this.s = s;
        }

        @Override
        public void run(){
            synchronized (s){
                System.out.println("In run");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
