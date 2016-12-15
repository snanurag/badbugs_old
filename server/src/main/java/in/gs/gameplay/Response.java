package in.gs.gameplay;

import java.util.ArrayList;
import java.util.List;

//      {
//        "rallies": [
//        {
//        "time": "5",
//        "rally": [
//        {
//        "type": 2,
//        "corner": 1,
//        "level": 3,
//        "angle": 12
//        },
//        {
//        "type": 5,
//        "corner": 2,
//        "level": 1,
//        "angle": 29
//        }
//        ]
//        },
//        {
//        "time": "432324234",
//        "rally": []
//        }
//        ]
//        }
public class Response {

    private List<Rally> rallies = new ArrayList<>();

    private int hardness;
    private boolean isHardnessIncreased;

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public boolean isHardnessIncreased() {
        return isHardnessIncreased;
    }

    public void setHardnessIncreased(boolean hardnessIncreased) {
        isHardnessIncreased = hardnessIncreased;
    }

    public List<Rally> getRallies() {
        return rallies;
    }

    public void setRallies(List<Rally> rallies) {
        this.rallies = rallies;
    }

    public class Rally {
        private float time; //0, 2.5, 5. 7.5, 10, 12.5 .... till 90.
        private List<Bug> rally = new ArrayList<>();

        public float getTime() {
            return time;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public List<Bug> getRally() {
            return rally;
        }

        public void setRally(List<Bug> rally) {
            this.rally = rally;
        }

    }

    public class Bug {
        private int type;
        private int corner;
        private int level;
        private int angle;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCorner() {
            return corner;
        }

        public void setCorner(int corner) {
            this.corner = corner;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }
    }
}

