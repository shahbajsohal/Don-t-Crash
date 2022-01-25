import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class GameOverPane extends GraphicsPane {
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program;
	private GImage screen;
	private GButton rect;
	private GButton rect2;
	private final int BUTTON_SIZE = 50;
	private final int X = 50;
	private final int Y = 50;
	
	long score;
	String name;

	public GameOverPane(MainApplication app) {
		super();
		program = app;
		screen = new GImage("AssetImages/Game Over.png",0,0);
		rect = new GButton("", X, ((Y*3)+Y/2), ((BUTTON_SIZE*4)+BUTTON_SIZE/5), BUTTON_SIZE);
		rect2 = new GButton("", ((X*10)+X/2), ((Y*3)+30), ((BUTTON_SIZE*4)+BUTTON_SIZE/5), BUTTON_SIZE);
		rect.setFillColor(Color.RED);
		rect.setVisible(false);
		rect2.setFillColor(Color.RED);
		rect2.setVisible(false);
	}
	
	public void setScore(long score) {
		//for aesthetic purposes, the score is multiplied by 1000
		long adjustedScore = score*1000;
		
		//score caps out at 999999
		if(adjustedScore>999999)
			adjustedScore=999999;
		
		this.score = adjustedScore;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void showContents() {
		program.add(screen);
		program.add(rect);
		program.add(rect2);
		System.out.println("Score: "+score+" Name: "+name);
		
		//checks if the player achieved a new highscore
		try {
			program.newHighScore(name, (int)score);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		program.updateScore();
	}

	@Override
	public void hideContents() {
		program.remove(screen);
		program.remove(rect);
		program.remove(rect2);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToMenu();
		}
		if (obj == rect2) {
			program.switchToGame();
		}
	}
}