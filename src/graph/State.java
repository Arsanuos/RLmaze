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
    private boolean partOfPath;
    private boolean containsPlayer;

    private List<Action> actions;

    public State(int i, int j, double value, boolean goal, boolean block){
        this.j = j;
        this.i = i;
        this.value = value;
        this.goal = goal;
        this.block = block;
        actions = new ArrayList<Action>();
    }

    public boolean isContainsPlayer() {
        return containsPlayer;
    }

    public void setContainsPlayer(boolean containsPlayer) {
        this.containsPlayer = containsPlayer;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }

    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
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

    @Override
    public boolean equals(Object obj){
        if(obj instanceof State){
            State state = (State) obj;
            if(state.getJ() == this.j && state.getI() == this.getI()){
                return true;
            }
        }
        return false;
    }

    @Override
    protected Object clone(){
        State state = new State(this.i, this.j, this.value, this.goal, this.block);
        state.setActions(this.getActions());
        state.setContainsPlayer(this.containsPlayer);
        state.setPartOfPath(this.partOfPath);
        return state;
    }
}
