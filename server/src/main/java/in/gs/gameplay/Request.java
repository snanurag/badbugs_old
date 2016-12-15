package in.gs.gameplay;

import in.gs.AbstractRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Request extends AbstractRequest {

    private int bugsmissed;
    private int type;
    private int nextRallyTime;
    private String gameId;

    private List<Touch> touches = new ArrayList<>();

    public int getBugsmissed() {
        return bugsmissed;
    }

    public void setBugsmissed(int bugsmissed) {
        this.bugsmissed = bugsmissed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Touch> getTouches() {
        return touches;
    }

    public void setTouches(List<Touch> touches) {
        this.touches = touches;
    }

    public int getNextRallyTime() {
        return nextRallyTime;
    }

    public void setNextRallyTime(int nextRallyTime) {
        this.nextRallyTime = nextRallyTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
