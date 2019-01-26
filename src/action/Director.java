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
        double val = -1 * Double.MAX_VALUE;
        for(State nState: neighbours){
            if(val < nState.getValue()){
                //System.out.println("" + nState.getI() + "-" + "" + nState.getJ());
                maxState = nState;
                val = nState.getValue();
            }
        }
        return maxState;
    }

    private static Action getRelativeAction(State state, State maxState){
        if(state.getI() == maxState.getI() && state.getJ() + 1 == maxState.getJ()){
            return new Right(Config.RIGHT);
        }else if(state.getI() == maxState.getI() && state.getJ() - 1 == maxState.getJ()){
            return new Left(Config.LEFT);
        }else if(state.getI() + 1 == maxState.getI()&& state.getJ() == maxState.getJ()){
            return new Down(Config.DOWN);
        }else if(state.getI() - 1 == maxState.getI() && state.getJ() == maxState.getJ()){
            return new Up(Config.UP);
        }
        throw new RuntimeException();
    }
}
