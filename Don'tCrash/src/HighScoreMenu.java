import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class HighScoreMenu extends GraphicsPane {
	private MainApplication program; 

	String names[];
	int scores[];
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	
	public static final String FONT = "Arial-Bold-40";
	
	GImage menu;
	
	GLabel score1;
	GLabel score2;
	GLabel score3;
	GLabel score4;
	GLabel score5;
	GLabel score6;
	GLabel score7;
	GLabel score8;
	GLabel score9;
	GLabel score10;
	
	GImage back;
	
	HighScore o;

	public HighScoreMenu(MainApplication app) {
		super();
		program = app;
		
		o = new HighScore();
		names = o.getNames();
		scores = o.getScores();
		
		labels();
		
		menu = new GImage("AssetImages/high score menu.png",0,0);
		menu.setSize(800,600);
		
		back = new GImage("AssetImages/back button.png", 0, 0);
		back.setSize(100, 50);
		back.setLocation(0, WINDOW_HEIGHT-back.getHeight());
	}
	
	public void newHighScore(String name, int score) throws IOException {
		o.newHighScore(name, score);
	}
	
	public void update() {
		program.remove(score1);
		program.remove(score2);
		program.remove(score3);
		program.remove(score4);
		program.remove(score5);
		program.remove(score6);
		program.remove(score7);
		program.remove(score8);
		program.remove(score9);
		program.remove(score10);
		labels();
	}
	
	public void labels(){
		score1 = new GLabel("1. "+names[9]+" .......... "+scores[9], 100, 100);
		score1.setColor(Color.yellow);
		score1.setFont(FONT);
		score1.setLocation(WINDOW_WIDTH/2-score1.getWidth()/2, 180);
		
		score2 = new GLabel("2. "+names[8]+" .......... "+scores[8], 100, 150);
		score2.setColor(Color.white);
		score2.setFont(FONT);
		score2.setLocation(WINDOW_WIDTH/2-score2.getWidth()/2, score1.getY()+40);
		
		score3 = new GLabel("3. "+names[7]+" .......... "+scores[7], 100, 200);
		score3.setColor(Color.white);
		score3.setFont(FONT);
		score3.setLocation(WINDOW_WIDTH/2-score3.getWidth()/2, score2.getY()+40);
		
		score4 = new GLabel("4. "+names[6]+" .......... "+scores[6], 100, 250);
		score4.setColor(Color.white);
		score4.setFont(FONT);
		score4.setLocation(WINDOW_WIDTH/2-score4.getWidth()/2, score3.getY()+40);
		
		score5 = new GLabel("5. "+names[5]+" .......... "+scores[5], 100, 300);
		score5.setColor(Color.white);
		score5.setFont(FONT);
		score5.setLocation(WINDOW_WIDTH/2-score5.getWidth()/2, score4.getY()+40);
		
		score6 = new GLabel("6. "+names[4]+" .......... "+scores[4], 100, 350);
		score6.setColor(Color.white);
		score6.setFont(FONT);
		score6.setLocation(WINDOW_WIDTH/2-score6.getWidth()/2, score5.getY()+40);
		
		score7 = new GLabel("7. "+names[3]+" .......... "+scores[3], 100, 400);
		score7.setColor(Color.white);
		score7.setFont(FONT);
		score7.setLocation(WINDOW_WIDTH/2-score7.getWidth()/2, score6.getY()+40);
		
		score8 = new GLabel("8. "+names[2]+" .......... "+scores[2], 100, 450);
		score8.setColor(Color.white);
		score8.setFont(FONT);
		score8.setLocation(WINDOW_WIDTH/2-score8.getWidth()/2, score7.getY()+40);
		
		score9 = new GLabel("9. "+names[1]+" .......... "+scores[1], 100, 500);
		score9.setColor(Color.white);
		score9.setFont(FONT);
		score9.setLocation(WINDOW_WIDTH/2-score9.getWidth()/2, score8.getY()+40);
		
		score10 = new GLabel("10. "+names[0]+" .......... "+scores[0], 100, 550);
		score10.setColor(Color.white);
		score10.setFont(FONT);
		score10.setLocation(WINDOW_WIDTH/2-score10.getWidth()/2, score9.getY()+40);
	}

	@Override
	public void showContents() {
		program.add(menu);
		program.add(score1);
		program.add(score2);
		program.add(score3);
		program.add(score4);
		program.add(score5);
		program.add(score6);
		program.add(score7);
		program.add(score8);
		program.add(score9);
		program.add(score10);
		program.add(back);
	}

	@Override
	public void hideContents() {
		program.remove(menu);
		program.remove(score1);
		program.remove(score2);
		program.remove(score3);
		program.remove(score4);
		program.remove(score5);
		program.remove(score6);
		program.remove(score7);
		program.remove(score8);
		program.remove(score9);
		program.remove(score10);
		program.remove(back);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == back) {
			program.switchToMenu();
		}
	}
}
