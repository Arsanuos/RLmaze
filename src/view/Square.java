// Dylan Van Assche - 3 ABA EI
package view;

import graph.State;

import java.awt.*;
import javax.swing.*;


public class Square extends JPanel {
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Square(State state) {
		this.setState(state);
		this.setLayout(new FlowLayout());
		this.add(new SquareViewElement(this.state));
		this.setVisible(true);
	}
}
