package graph;

import action.*;
import utils.Config;
import utils.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StateManager {

    private HashMap<Query, State> graph;
    private List<State> states;

    private static StateManager ourInstance = new StateManager();

    public static StateManager getInstance() {
        return ourInstance;
    }

    public void initGraph(){
        List<Action> actions = new ArrayList<>();
        actions.add(new Up(Config.UP));
        actions.add(new Down(Config.DOWN));
        actions.add(new Left(Config.LEFT));
        actions.add(new Right(Config.RIGHT));
        for(int i= 0 ; i < Config.N ; i++){
            for(int j = 0 ;j < Config.N ; j++){
                State state = new State(i, j, 0);
                state.setActions(actions);
                graph.put(new Query(i, j), state);
                states.add(state);
            }
        }
    }
    private StateManager() {
        this.states = new ArrayList<State>();
    }

    public State getState(Query q, State state){
        if(graph.containsKey(q)){
            return graph.get(q);
        }
        return state;
    }

    public List<State> getAllStates(){
        return states;
    }

    public List<Action> getAllActions(){
        List<Action> actions = new ArrayList<>();
        for(State state: states){
            for(Action action: actions){
                actions.add(action);
            }
        }
        return actions;
    }

    public List<State> getNeighbours(State state){
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, 1, -1};
        List<State> neighbours = new ArrayList<>();
        for(int i = 0 ;i < di.length; i++){
            Query q = new Query(state.getI() + di[i], state.getJ() + dj[i]);
            neighbours.add(getState(q, state));
        }
        return neighbours;
    }

    public boolean sameActions(List<Action> actions){
        List<Action> currActions = this.getAllActions();
        if(actions.size() == currActions.size()){
            for(int i = 0 ;i < actions.size(); i++){
                Action cur = currActions.get(i);
                Action commingAction = actions.get(i);
                if(!cur.equals(commingAction))
                    return false;
            }
            return true;
        }
        return false;
    }
}
