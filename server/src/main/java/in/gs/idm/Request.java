package in.gs.idm;

import java.util.Map;

/**
 * Created by ashrinag on 12/2/2016.
 */
public class Request {


    private String id;
    private int age;
    private Map<String, Integer> powers;
    private int busy;
    private int totalBugs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Integer> getPowers() {
        return powers;
    }

    public void setPowers(Map<String, Integer> powers) {
        this.powers = powers;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    public int getTotalBugs() {
        return totalBugs;
    }

    public void setTotalBugs(int totalBugs) {
        this.totalBugs = totalBugs;
    }

    public enum POWER {
        SILVER_SPELL("1"), GOLD_SPELL("2"), GUM("3"), BUG_TRAP("4"), TORCH("5"), BURNING_THREAD("6"), NET("7"), BURNING_NET("8"), BOMB("9");

        String type;

        POWER(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
