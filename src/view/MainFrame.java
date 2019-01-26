// Dylan Van Assche - 3 ABA EI
package view;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainFrame extends JFrame {


	private MazeView mazeView;
	private JScrollPane scrollView;

	public MazeView getMazeView() {
		return mazeView;
	}

	public void setMazeView(MazeView mazeView) {
		this.mazeView = mazeView;
	}

	public JScrollPane getScrollView() {
		return scrollView;
	}

	public void setScrollView(JScrollPane scrollView) {
		this.scrollView = scrollView;
	}

	public MainFrame() {
		this.getContentPane().setLayout(new BorderLayout());
		this.newGame();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void newGame() {
		this.getContentPane().removeAll(); // clean it up
		this.setMazeView(new MazeView());
		this.setScrollView(new JScrollPane(this.getMazeView()));
		this.getMazeView().setAutoscrolls(true);
		this.getContentPane().add(this.getScrollView(), BorderLayout.CENTER);
		// Revalidates the component hierarchy, when adding/removing stuff at runtime you need to reload the UI, 
		// this is NOT repaint since we add/remove the components completely without modifying their properties!
		// If you modify their properties only, a repaint() is sufficient!
		this.revalidate();
	}
	
	public void updateGame() {
		// Updating the location of the Player requires only a repainting of the components.
		//this.getMazeView().newGame();
		this.revalidate();
		this.repaint();
	}
}
