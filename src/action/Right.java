package action;

import graph.State;
import graph.StateManager;
import utils.Query;

public class Right extends Action {
    @Override
    public State getNextState(State state) {
        return StateManager.getInstance().getState(new Query(state.getI(), state.getJ() + 1));
    }
}
