package graph;

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

    public void ConstructGraph(){
        for(int i= 0 ; i < Config.N ; i++){
            for(int j = 0 ;j < Config.N ; j++){
                State state = new State(i, j, 0);
                graph.put(new Query(i, j), state);
                states.add(state);
            }
        }
    }
    private StateManager() {
        this.states = new ArrayList<State>();
    }

    public State getState(Query q){
        return graph.get(q);
    }

    public List<State> getAllStates(){
        return states;
    }
}
