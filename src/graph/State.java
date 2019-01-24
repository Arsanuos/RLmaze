package graph;

import action.Action;

import java.util.ArrayList;
import java.util.List;

public class State {

    private int i;
    private int j;
    private double value;

    private List<Action> actions;

    public State(int i, int j, double value){
        this.j = j;
        this.i = i;
        this.value = value;
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
