package graph;

import action.Action;

import java.util.ArrayList;
import java.util.List;

public class State {

    private int i;
    private int j;
    private double value;
    private boolean block;
    private boolean goal;

    private List<Action> actions;

    public State(int i, int j, double value, boolean goal, boolean block){
        this.j = j;
        this.i = i;
        this.value = value;
        this.goal = goal;
        this.block = block;
        actions = new ArrayList<Action>();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        block = block;
    }

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
