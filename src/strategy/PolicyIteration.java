package strategy;

import action.Action;
import action.Director;
import graph.State;
import graph.StateManager;
import utils.Config;

import java.util.ArrayList;
import java.util.List;

public class PolicyIteration implements policyIF{


    @Override
    public void eval() {
        StateManager stateManager = StateManager.getInstance();
        List<Action> prevActions = stateManager.getAllActions();
        int i = 0;
        while(i == 0 && !stateManager.sameActions(prevActions)){
            modifyValues();
            i += 1;
            prevActions = stateManager.getAllActions();
            improvePolicy();
        }
    }

    private void modifyValues(){
        StateManager stateManager = StateManager.getInstance();
        for(int k = 0 ;k < Config.POLICY_ITERATION; k++){
            for(State state: stateManager.getAllStates()){
                List<Action> actions = state.getActions();
                double val = 0;
                double pi = 1/actions.size();
                for(Action action: actions){
                    val += pi * (Config.R + Config.EPS * action.getNextStateValue(state));
                }
                state.setValue(val);
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
