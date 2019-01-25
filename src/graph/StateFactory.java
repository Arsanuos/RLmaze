package graph;

import utils.Config;

public class StateFactory {

    public static State getState(Config.StateTypes stateType, int i, int j){
        if(stateType == Config.StateTypes.NORMAL_STATE){
            return new EmptyState(i, j, Config.STATE_INIT_VAL);
        }else if(stateType == Config.StateTypes.BLOCK){
            return new Block(i, j);
        }else if(stateType == Config.StateTypes.GOAL){
            return new Goal(i, j);
        }
        throw new RuntimeException();
    }

}
