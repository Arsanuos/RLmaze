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

    public static StateManager getInstance() {

        return ourInstance;
    }
    private StateManager() {
        this.states = new ArrayList<State>();
        this.graph = new HashMap<>();
    }

    public void initGraph(){
        //getBlockStates();
        Random rand = new Random();
        int cnt = Config.BOLCKS_NUMBER;
        System.out.println(Config.BOLCKS_NUMBER);
        for(int i= 0 ; i < Config.N ; i++){
            for(int j = 0 ;j < Config.N ; j++){
                int x = rand.nextInt(2);
                if(x == 1 && cnt != 0 && (i != 0 && j != 0) && (i != Config.N - 1 && j != Config.N - 1)){
                    cnt--;
                    State state = StateFactory.getState(Config.StateTypes.BLOCK, i, j);
                    addState(state);
                }else if(i == Config.N - 1  && j == Config.N - 1){
                    addState(StateFactory.getState(Config.StateTypes.GOAL, i, j ));
                }else{
                    addState(StateFactory.getState(Config.StateTypes.NORMAL_STATE, i, j));
                }
            }
        }
        this.getAllStates().get(0).setContainsPlayer(true);
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
            State newState = getState(q, state);
            if(!newState.equals(state)){
                neighbours.add(newState);
            }
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
        }
    }

    private void addState(State state){
        if(!(state instanceof graph.Block)){
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

    public void printGrid(){
        System.out.println("============================================");
        for(int i=0 ;i < Config.N; i++){
            for(int j = 0 ;j < Config.N; j++){
                System.out.print(graph.get(new Query(i, j)).getValue() + " ");
            }
            System.out.println();
        }
    }

    public void printAction(){
        for(int i=0 ;i < Config.N; i++){
            for(int j = 0 ;j < Config.N; j++){
                State state = graph.get(new Query(i, j));
                if(!state.isBlock()){
                    System.out.print(state.getActions().get(0).getType() + " ");
                }else{
                    System.out.print("NONE ");
                }
            }
            System.out.println();
        }
    }

}
