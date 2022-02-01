import java.util.ArrayList;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.Program;

public class PlayPane extends GraphicsPane implements KeyListener, ActionListener {
	private MainApplication program; 
	boolean gameOver = false;
	private Timer timer;
	
	//car entities
	ArrayList<EnemyCar> cars;
	PlayerCar player;
	EnemyCar enemy;
	Traffic traf;
	
	//background
	GImage background;
	GImage road;
	GImage roadOutline;
	
	//healthbar
	ArrayList<GRect> healthBar;
	GRect health1;
	GRect health2;
	GRect health3;
	GRect health4;
	
	//health label
	int health;
	int lastHealth;
	GLabel healthLabel;
	
	//pause function
	GRect rectangle;
	boolean paused;
	GLabel pause;
	GImage pauseScreen;
	long totalTime = 0;
	
	//game function
	long score;
	String level;
	long startTime;
	
	//UI
	String scoreString;
	GLabel scoreLabel;
	GLabel levelLabel;
	GImage powerup;
	GImage cooldown;
	GImage pauseIndicator;
	GImage invicibilityIndicator;
	
	//delays
	int delayHealth;
	int delayPower;

	public PlayPane(MainApplication app){
		super();
		program = app;
		
		traf = new Traffic(app, this);
		
		player = new PlayerCar(app, this);
		
		
		scoreLabel = new GLabel("Score: 0", 0, 200);
		scoreLabel.setFont("Arial-Bold-22");
		scoreLabel.setColor(Color.pink);
		
		powerup = new GImage("AssetImages/powerup indicator.png",0,0);
		cooldown = new GImage("AssetImages/cooldown indicator.png",0,0);
		invicibilityIndicator = new GImage("AssetImages/invincible indicator.png",0,0);
		pauseIndicator = new GImage("AssetImages/pause indicator.png",0,0);
		
		pauseScreen = new GImage("AssetImages/Pause Screen.png",0,0);
		
		level = "Level: 1";
		levelLabel = new GLabel(level, 0, scoreLabel.getY()+scoreLabel.getHeight());
		levelLabel.setFont("Arial-Bold-22");
		levelLabel.setColor(Color.pink);
		
		health = 4;
		lastHealth = health; 
		
		health1 = new GRect(5, levelLabel.getY()+levelLabel.getHeight()+20, 20, 20);
		health1.setColor(Color.black);
		health1.setFillColor(Color.red);
		health1.setFilled(true);
		
		health2 = new GRect(25, levelLabel.getY()+levelLabel.getHeight()+20, 20, 20);
		health2.setColor(Color.black);
		health2.setFillColor(Color.red);
		health2.setFilled(true);
		
		
		health3 = new GRect(45, levelLabel.getY()+levelLabel.getHeight()+20, 20, 20);
		health3.setColor(Color.black);
		health3.setFillColor(Color.red);
		health3.setFilled(true);
		
		
		health4 = new GRect(65, levelLabel.getY()+levelLabel.getHeight()+20, 20, 20);
		health4.setColor(Color.black);
		health4.setFillColor(Color.red);
		health4.setFilled(true);
		
		
		healthBar = new ArrayList<GRect>();
		healthBar.add(health1);
		healthBar.add(health2);
		healthBar.add(health3);
		healthBar.add(health4);
		
		healthLabel = new GLabel("Health: "+Integer.toString(health),0,levelLabel.getY()+levelLabel.getHeight());
		healthLabel.setFont("Arial-Bold-22");
		healthLabel.setColor(Color.pink);
		
		background = new GImage("AssetImages/galaxy2.gif",0,0);
		background.setSize(800,600);
		
		
		road = new GImage("AssetImages/Final Road 2L3S.gif",150,0);
		road.setSize(500,600);
		
		roadOutline = new GImage("AssetImages/road outline.png",0,0);

		delayHealth = 1;
		delayPower = 0;
	}
	
	//resets the game's functions once the player plays again
	public void reset(){
		player = new PlayerCar(program, this);
		traf = new Traffic(program, this);
		score = 0;
		health = 4;
		level = "Level: 1";
		levelLabel.setLabel(level);
		delayHealth = 1;
		score = 0;
		totalTime = 0;
		delayPower = 0;
		resetHealth();
	}
	
	@Override
	public void showContents() {
		reset();
		player.setPlayerCar();
		program.add(background);
		program.add(roadOutline);
		program.add(road);
		program.add(scoreLabel);
		program.add(levelLabel);
		program.add(healthLabel);
		program.add(pauseIndicator);
		show();
		player.show();
		timer = new Timer(10, this);
		timer.start();
		startTime = System.currentTimeMillis();
	}

	@Override
	public void hideContents() {
		timer.stop();
		player.hide();
		program.remove(background);
		program.remove(road);
		program.remove(roadOutline);
		program.remove(scoreLabel);
		program.remove(levelLabel);
		program.remove(healthLabel);
		program.remove(pauseIndicator);
		program.remove(invicibilityIndicator);
		program.remove(cooldown);
		traf.hide();
		hide();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(paused) {
			return;
		}
		
		//powerup manager
		if(delayPower>0)
			delayPower--;
		else {
			program.remove(cooldown);
			program.add(powerup);
		}
		
		//update player
		player.update();
		
		//update enemies
		traf.update();
		
		cars = traf.getCars();
		
		if(health != lastHealth) {
			updateHealth();
		}
		//update map
		
		//update labels
		score = ((System.currentTimeMillis()-startTime)/1000)+totalTime;
		scoreString = String.valueOf("Score: "+score);
		scoreLabel.setLabel(scoreString);
		
		levelCounter();
		levelLabel.setLabel(level);
		
		healthLabel.setLabel("Health: "+health);
		
		//invincibility implementation that lasts for a few seconds when player is hit
		delayHealth--;
		
		if(delayHealth <= 0) {
			delayHealth = 0;
			program.remove(invicibilityIndicator);
		}
		
		//run through enemy array to check with collision function
		for(EnemyCar enemy : cars) {
			if(collision(player.getImage(), enemy.getImage())) {
				//when player is hit
				if(delayHealth==0) {
					health--;
					//System.out.println("OUCH!");
					delayHealth = 120; //player is invincible for a few seconds
					program.add(invicibilityIndicator);
				}
			}
		}
		
		if(health==0) {
			program.setScore(score);
			program.switchToNameInput();
		}
	}
	
	public void levelCounter() {
		if(score==15){
			level = "Level: 2";
		}
		else if(score==30){
			level = "Level: 3";
		}
		else if(score==45){
			level = "Level: 4";
		}
		else if(score==60){
			level = "Level: 5";
		}
	}
	
	public void showPaused() {
		if(paused) {
			program.add(pauseScreen);
			totalTime += (System.currentTimeMillis()-startTime)/1000;
		}
		else {
			program.remove(pauseScreen);
			startTime = System.currentTimeMillis();
		}
	}
	
	//getter
	public long getScore() {
		return score;
	}
	
	//collision implementation
	public boolean collision(GImage boxA, GImage boxB) {
		double minX = boxA.getX();
		double minY = boxA.getY();
		double maxX = minX + boxA.getWidth();
		double maxY = minY + boxA.getHeight();
		
		if(boxB.getX() > maxX || minX > boxB.getX() + boxB.getWidth()) return false;
		if(boxB.getY() > maxY || minY > boxB.getY() + boxB.getHeight()) return false;
		return true;
	}
	
	//health bar implementation
	public void show() {
		program.add(health1);
		program.add(health2);
		program.add(health3);
		program.add(health4);
		
	}
	
	public void hide() {
		program.remove(health1);
		program.remove(health2);
		program.remove(health3);
		program.remove(health4);
	}
	
	void resetHealth() {
		show();
		for(int i = 0; i < health; i++) {
			healthBar.get(i).setFilled(true);
		}
	}
	
	void updateHealth() {
		if(lastHealth > health) {
			healthBar.get(health).setFilled(false);
		}
		else {
			healthBar.get(health-1).setFilled(true);
		}
		lastHealth = health;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			player.updateDX(-5);
		}
		else if(key == KeyEvent.VK_RIGHT) {
			player.updateDX(5);
		}
		else if(key == KeyEvent.VK_ESCAPE) {
			//pauses game
			paused = !paused;
			showPaused();
		}
		else if(key == KeyEvent.VK_SPACE) {
			//USE HEALTH POWERUP LIMITED TO EVERY 20-ish SECONDS
			if(health<4 && delayPower == 0) {
				health++;
				delayPower = 2000; //20-ish seconds
				program.remove(powerup);
				program.add(cooldown);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		player.stopDX();
	}
}
