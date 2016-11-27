package in.gs.search;

import in.gs.AbstractRequest;

import java.util.AbstractCollection;

/**
 * Created by ashrinag on 11/26/2016.
 */
public class Request extends AbstractRequest{

    private String age;
    private String playerid;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }
}
