// Dylan Van Assche - 3 ABA EI
package view;

import java.awt.*;
import javax.swing.*;
import graph.State;
import graph.StateManager;
import utils.Config;

public class MazeView extends JPanel {


	public MazeView() {
		this.setLayout(new GridLayout(0, Config.N, 0, 0)); // gap = 0
		this.newGame();
		this.setVisible(true);
	}

	public void newGame() {
		this.removeAll();
		for(int i = 0; i < Config.N * Config.N; i++) {
			this.add(new Square(StateManager.getInstance().getAllStates().get(i)));
		}
	}
}
