package action;

import graph.State;
import graph.StateManager;
import utils.Query;

public class Left extends Action {


    public Left(String type){
        super(type);
    }

    @Override
    public State getNextState(State state) {
        int j = state.getJ() - 1 >= 0 ? state.getJ() - 1 : 0;
        return StateManager.getInstance().getState(new Query(state.getI(), j), state);
    }
}