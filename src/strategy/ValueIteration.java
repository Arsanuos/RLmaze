package strategy;

import action.Action;
import graph.State;
import graph.StateManager;
import utils.Config;

import java.util.List;

public class ValueIteration implements policyIF{

    @Override
    public void eval() {
        Optimizer optimizer = new Optimizer();
        List<State> prevState;
        prevState = StateManager.getInstance().getClonedStates();
        int i = 0;
        while(i== 0 || !StateManager.getInstance().sameStatesValues(prevState)){
            i++;
            if(i > Config.N*Config.N)break;
            prevState = StateManager.getInstance().getClonedStates();
            for(State state: StateManager.getInstance().getAllStates()){
                if(!state.isBlock() && !state.isGoal()){
                    double val = -1 * Double.MAX_VALUE;
                    for(Action action: state.getActions()){
                        val = Double.max(val, Config.R + Config.EPS * action.getNextStateValue(state));
                    }
                    state.setValue(val);
                }
            }
        }
        optimizer.improvePolicy();
        StateManager.getInstance().printGrid();
        StateManager.getInstance().printAction();
        optimizer.checkSolvable();
    }

}
