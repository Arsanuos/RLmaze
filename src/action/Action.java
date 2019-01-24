package action;

import graph.State;

public class Action {

    private String type;

    public Action(String type){
        this.type = type;
    }

    public State getNextState(State state){
        return null;
    }

    public double getNextStateValue(State state){
        return this.getNextState(state).getValue();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Action action = (Action) obj;
        if (this.type == action.getType()){
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }
}
