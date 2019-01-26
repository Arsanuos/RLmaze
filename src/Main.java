import action.Action;
import controller.Game;
import graph.State;
import graph.StateManager;
import utils.Config;
import utils.Query;
import view.MainFrame;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame(Config.POLICY.POLICY_ITERATION);
        MainFrame mainFrame = new MainFrame();
        //mainFrame.newGame();
        State state = StateManager.getInstance().getState(new Query(0, 0), null);
        Action action = null;
        while(!state.isGoal()){
            action = state.getActions().get(0);
            state.setContainsPlayer(false);
            state.setPartOfPath(true);
            state = action.getNextState(state);
            state.setContainsPlayer(true);
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (Exception e){

            }
            mainFrame.updateGame();
        }
    }
}
