package controller;

import graph.StateManager;
import strategy.PolicyFactory;
import utils.Config;

public class Game {

    public void startGame(Config.POLICY policy){
        StateManager stateManager = StateManager.getInstance();
        stateManager.initGraph();
        while (true){
            try{
                PolicyFactory.getPolicy(policy).eval();
                break;
            }catch (Exception e){
                stateManager.initGraph();
            }
        }
    }
}
