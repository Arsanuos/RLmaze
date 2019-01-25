package action;

import graph.State;
import utils.Config;

import java.util.List;

public class Director {

    public static Action getMaxAction(State state, List<State> neighbours){
        State maxState = getMaxState(neighbours);
        return getRelativeAction(state, maxState);
    }

    private static State getMaxState(List<State> neighbours){
        State maxState = null;
        double val = Double.MIN_VALUE;
        for(State nState: neighbours){
            if(val < nState.getValue()){
                maxState = nState;
                val = nState.getValue();
            }
        }
        return maxState;
    }

    private static Action getRelativeAction(State state, State maxState){
        if(state.getI() == maxState.getI() && state.getJ() == maxState.getJ() - 1){
            return new Right(Config.RIGHT);
        }else if(state.getI() == maxState.getI() && state.getJ() == maxState.getJ() + 1){
            return new Left(Config.LEFT);
        }else if(state.getI() == maxState.getI() - 1 && state.getJ() == maxState.getJ()){
            return new Up(Config.UP);
        }else if(state.getI() == maxState.getI() + 1 && state.getJ() == maxState.getJ()){
            return new Down(Config.DOWN);
        }
        throw new RuntimeException();
    }
}
