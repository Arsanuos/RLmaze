package graph;

import action.*;
import jdk.nashorn.internal.ir.Block;
import utils.Config;
import utils.Query;

import java.util.*;

public class StateManager {

    private HashMap<Query, State> graph;
    private List<State> states;

    private static StateManager ourInstance = new StateManager();
    private StateFactory stateFactory;

    public static StateManager getInstance() {

        return ourInstance;
    }
    private StateManager() {
        this.states = new ArrayList<State>();
        this.stateFactory = new StateFactory();
        this.graph = new HashMap<>();
    }

    public void initGraph(){
        getBlockStates();
        for(int i= 0 ; i < Config.N ; i++){
            for(int j = 0 ;j < Config.N ; j++){
                Query q = new Query(i, j);
                if(!graph.containsKey(q)){
                    addState(StateFactory.getState(Config.StateTypes.NORMAL_STATE, i, j));
                }
            }
        }
    }


    public State getState(Query q, State state){
        if(graph.containsKey(q)){
            State req = graph.get(q);
            if(req.isBlock()){
                return state;
            }
            return req;
        }
        return state;
    }

    public List<State> getAllStates(){
        return states;
    }

    public List<Action> getAllActions(){
        List<Action> actions = new ArrayList<>();
        for(State state: states){
            actions.addAll(state.getActions());
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

    private void getBlockStates(){
        List<State> blocks = new ArrayList<>();
        Set<Query> hash_Set = new HashSet<Query>();
        Random rand = new Random();
        for(int i = 0 ;i < Config.BOLCKS_NUMBER; i++){
            int x,y = -1;
            do{
                x = rand.nextInt(Config.N - 1);
                y = rand.nextInt(Config.N - 1);
            }while(hash_Set.contains(new Query(x, y)));
            State state = StateFactory.getState(Config.StateTypes.BLOCK, x, y);
            blocks.add(state);
            states.add(state);
        }
    }

    private void addState(State state){
        if(state instanceof State){
            addActionsTo(state);
        }
        this.states.add(state);
        this.graph.put(new Query(state.getI(), state.getJ()), state);
    }

    private void addActionsTo(State state){
        List<Action> actions = new ArrayList<>();
        actions.add(new Up(Config.UP));
        actions.add(new Down(Config.DOWN));
        actions.add(new Left(Config.LEFT));
        actions.add(new Right(Config.RIGHT));
        state.setActions(actions);
    }
}
