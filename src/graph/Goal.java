package graph;
import graph.State;
import utils.Config;

public class Goal extends State {


    public Goal(int i, int j) {
        super(i, j, Config.GOAL_VAL, true, false);
    }
}
