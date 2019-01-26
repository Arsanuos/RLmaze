package strategy;

import action.Action;
import action.Director;
import graph.State;
import graph.StateManager;
import utils.Config;
import utils.Query;

import java.util.List;

public class Optimizer {

    public void improvePolicy(){
        StateManager stateManager = StateManager.getInstance();
        for(State state: stateManager.getAllStates()){
            List<State> neighbours = stateManager.getNeighbours(state);
            Action action = Director.getMaxAction(state, neighbours);
            state.getActions().clear();
            state.getActions().add(action);
        }
    }

    public void checkSolvable(){
        State state = StateManager.getInstance().getState(new Query(0, 0), null);
        Action action = null;
        int cnt = 0;
        while(!state.isGoal()){
            action = state.getActions().get(0);
            state.setContainsPlayer(false);
            state = action.getNextState(state);
            state.setContainsPlayer(true);
            cnt++;
            if(cnt > Config.N * Config.N + 5){
                throw new RuntimeException();
            }
        }
        state.setContainsPlayer(false);
        StateManager.getInstance().getState(new Query(0, 0), null).setContainsPlayer(true);
    }

}
