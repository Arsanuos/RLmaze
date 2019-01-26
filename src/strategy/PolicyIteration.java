package strategy;

import action.Action;
import action.Director;
import graph.State;
import graph.StateManager;
import utils.Config;
import utils.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PolicyIteration implements policyIF{


    @Override
    public void eval() {
        StateManager stateManager = StateManager.getInstance();
        List<Action> prevActions = stateManager.getAllActions();
        int i = 0;
        while(i == 0 || !stateManager.sameActions(prevActions)){
            modifyValues();
            StateManager.getInstance().printGrid();
            StateManager.getInstance().printAction();
            i += 1;
            prevActions = stateManager.getAllActions();
            improvePolicy();
        }
        StateManager.getInstance().printGrid();
        StateManager.getInstance().printAction();
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

    private void modifyValues(){
        StateManager stateManager = StateManager.getInstance();
        for(int k = 0 ;k < Config.POLICY_ITERATION; k++){
            for(State state: stateManager.getAllStates()){
               if(!state.isBlock() && !state.isGoal()){
                   List<Action> actions = state.getActions();
                   double val = 0;
                   double pi = 1.0/actions.size();
                   for(Action action: actions){
                       val += pi * (Config.R + Config.EPS * action.getNextStateValue(state));
                   }
                   state.setValue(val);
               }
            }
        }
    }

    private void improvePolicy(){
        StateManager stateManager = StateManager.getInstance();
        for(State state: stateManager.getAllStates()){
            List<State> neighbours = stateManager.getNeighbours(state);
            Action action = Director.getMaxAction(state, neighbours);
            state.getActions().clear();
            state.getActions().add(action);
        }
    }

}
