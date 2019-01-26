package utils;

public class Config {
    public static int N = 10;
    public static double EPS = 1;
    public static double R = -1;
    public static double POLICY_ITERATION = 100;
    public static String UP = "UP";
    public static String DOWN = "DOWN";
    public static String LEFT = "LEFT";
    public static String RIGHT = "RIGHT";
    public static double GOAL_VAL = 0;
    public enum StateTypes{
        NORMAL_STATE, GOAL, BLOCK;
    }
    public static double STATE_INIT_VAL = 0;
    public static int BOLCKS_NUMBER = (int)((float)30/100 * Config.N*Config.N);
    public enum POLICY{
        POLICY_ITERATION, VALUE_ITERATION;
    }
    public static double VALUE_ITERATION_THRESHOLD_EQUALITY = .001;

}
