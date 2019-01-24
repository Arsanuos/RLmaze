package action;

import graph.State;

public class Action {

    public State getNextState(State state){
        return null;
    }

    public double getNextStateValue(State state){
        return this.getNextState(state).getValue();
    }
}
