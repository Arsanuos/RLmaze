package action;

import graph.State;
import graph.StateManager;
import utils.Config;
import utils.Query;

public class Down extends Action {

    public Down(String type){
        super(type);
    }

    @Override
    public State getNextState(State state) {
        int i = state.getI() + 1 < Config.N ? state.getI() + 1 : state.getI();
        return StateManager.getInstance().getState(new Query(i, state.getJ()), state);
    }
}
