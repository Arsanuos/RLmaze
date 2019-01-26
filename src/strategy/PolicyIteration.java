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
        Optimizer optimizer = new Optimizer();
        List<Action> prevActions = stateManager.getAllActions();
        int i = 0;

        while(i == 0 || !stateManager.sameActions(prevActions)){
            modifyValues();
            i += 1;
            prevActions = stateManager.getAllActions();
            optimizer.improvePolicy();
        }
        StateManager.getInstance().printGrid();
        StateManager.getInstance().printAction();
        optimizer.checkSolvable();
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


}
