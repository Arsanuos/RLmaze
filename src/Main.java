import controller.Game;
import utils.Config;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame(Config.POLICY.POLICY_ITERATION);
        MainFrame mainFrame = new MainFrame();
        //mainFrame.newGame();
        mainFrame.updateGame();
    }
}
