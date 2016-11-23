package in.gs.gameplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 11/23/2016.
 */
public class Request {

    private int bugsmissed;
    private int type;
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

}
