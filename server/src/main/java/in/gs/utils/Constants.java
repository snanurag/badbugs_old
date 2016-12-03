package in.gs.utils;

/**
 * Created by ashrinag on 11/26/2016.
 */
public class Constants {

    public static final String MAKE_AVAILABLE = "makeavailable";


    public static final String TABLE_GAMES = "Games";
    public static final String TABLE_PLAYER_GAME_MAPPING = "Players_Game";


    public static enum TABLE_AVAILABILITY {
        PRIMARY_KEY("age"), SORT_KEY("last_available_time"), PLAYER_ID("playerid");
        String value;
        TABLE_AVAILABILITY(String value){
            this.value = value;
        }

        public String value(){
            return  value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static String tableName(){
            return "availability";
        }

        public static int readLimit(){
            return 85;
        }
    }

    public static enum TABLE_PLAYERS {
        PRIMARY_KEY("id"), AGE("age"), POWERS("powers"), BUSY("busy"), TOTAL_BUGS("totalbugs");
        String value;

        TABLE_PLAYERS(String value) {
            this.value = value;
        }

        public String value(){
            return  value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static String tableName() {
            return "Players";
        }
    }
}
