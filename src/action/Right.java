package action;

import graph.State;
import graph.StateManager;
import utils.Config;
import utils.Query;

public class Right extends Action {

    public Right(String type){
        super(type);
    }


    @Override
    public State getNextState(State state) {
        int j = state.getJ() + 1 < Config.N ? state.getJ() + 1 : state.getJ();
        return StateManager.getInstance().getState(new Query(state.getI(), j), state);
    }
}
