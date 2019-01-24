package action;

import graph.State;
import graph.StateManager;
import utils.Query;

public class Up extends Action {

    public Up(String type){
        super(type);
    }


    @Override
    public State getNextState(State state) {
        int i = state.getI() - 1 >= 0 ? state.getI() - 1 : state.getI();
        return StateManager.getInstance().getState(new Query(i, state.getJ()), state);
    }
}