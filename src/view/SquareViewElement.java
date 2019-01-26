package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import graph.State;


public class SquareViewElement extends JComponent {

	private State state;
	private static final String WALL_PATH = "src/images/wall.png";
	private static final String PLAYER_PATH = "src/images/amr_hendy.jpeg";
	private static final String GOLD_PATH = "src/images/gold.png";
	private static final String FREE_PATH = "src/images/free.png";

	private static final String GOOGLE_PATH = "src/images/google.png";
	private static final String FACEBOOK = "src/images/facebook.jpg";
	private static final String AMAZON  = "src/images/amazon.png";
	private static final String MICORSOFT = "src/images/microsoft.png";

	private static final int IMG_SIZE = 64;
	private BufferedImage wallImg;
	private BufferedImage playerImg;
	private BufferedImage goldImg;
	private BufferedImage freeImg;


	private BufferedImage google;
	private BufferedImage facebook;
	private BufferedImage amazon;
	private BufferedImage microsoft;
    private int x;

    public BufferedImage getMicrosoft() {
        return microsoft;
    }

    public void setMicrosoft(BufferedImage microsoft) {
        this.microsoft = microsoft;
    }

	public BufferedImage getGoogle() {
		return google;
	}

	public void setGoogle(BufferedImage google) {
		this.google = google;
	}

	public BufferedImage getFacebook() {
		return facebook;
	}

	public void setFacebook(BufferedImage facebook) {
		this.facebook = facebook;
	}

	public BufferedImage getAmazon() {
		return amazon;
	}

	public void setAmazon(BufferedImage amazon) {
		this.amazon = amazon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public BufferedImage getWallImg() {
		return wallImg;
	}

	public void setWallImg(BufferedImage wallImg) {
		this.wallImg = wallImg;
	}

	public BufferedImage getPlayerImg() {
		return playerImg;
	}

	public void setPlayerImg(BufferedImage playerImg) {
		this.playerImg = playerImg;
	}

	public BufferedImage getGoldImg() {
		return goldImg;
	}

	public void setGoldImg(BufferedImage goldImg) {
		this.goldImg = goldImg;
	}

	public BufferedImage getFreeImg() {
		return freeImg;
	}

	public void setFreeImg(BufferedImage freeImg) {
		this.freeImg = freeImg;
	}

	public SquareViewElement(State state) {
		this.setState(state);
		try {
			Random rand = new Random();
			this.x = rand.nextInt(4);
			this.setWallImg(ImageIO.read(new File(WALL_PATH)));
			this.setPlayerImg(ImageIO.read(new File(PLAYER_PATH)));
			this.setGoldImg(ImageIO.read(new File(GOLD_PATH)));
			this.setFreeImg(ImageIO.read(new File(FREE_PATH)));

			this.setGoogle(ImageIO.read(new File(GOOGLE_PATH)));
			this.setFacebook(ImageIO.read(new File(FACEBOOK)));
			this.setAmazon(ImageIO.read(new File(AMAZON)));
			this.setMicrosoft(ImageIO.read(new File(MICORSOFT)));


		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(IMG_SIZE,IMG_SIZE);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if(this.getState().isBlock()){
			graphics.drawImage(this.getWallImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
		}else if(this.state.isContainsPlayer()){
			graphics.drawImage(this.getPlayerImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
		}else if(this.state.isGoal()){
			graphics.drawImage(this.getGoldImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
		}else if(this.state.isPartOfPath()){

			if(x == 0){
				graphics.drawImage(this.getGoogle(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			}else if(x == 1){
				graphics.drawImage(this.getAmazon(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			}else if(x == 2){
                graphics.drawImage(this.getMicrosoft(), 0, 0, IMG_SIZE, IMG_SIZE, null);
            }else{
				graphics.drawImage(this.getFacebook(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			}
		}else {
			graphics.drawImage(this.getFreeImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
		}
	}
}
