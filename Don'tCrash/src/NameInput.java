import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class NameInput extends GraphicsPane implements KeyListener, ActionListener{
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
								
	private GButton rect;
	private GImage screen;
	
	private Timer timer;
	
	char name[];
	int i;
	int key;
	
	GLabel letter1;
	GLabel letter2;
	GLabel letter3;
	GLabel letter4;
	GLabel letter5;
	GLabel letter6;
	
	String realName;
	
	char c;

	public NameInput(MainApplication app) {
		super();
		program = app;
		screen = new GImage("AssetImages/Name Input Menu.png",0,0);
		rect = new GButton("NEXT",800,600,200,100);
		rect.setLocation(800-rect.getWidth()-50,600-rect.getHeight()-50);
		rect.setFillColor(Color.yellow);
		name = new char[6];
		i = 0;
		
		letter1 = new GLabel("?",50,360);
		letter1.setFont("Arial-Black-90");
		letter1.setColor(Color.yellow);
		
		letter2 = new GLabel("?",180,360);
		letter2.setFont("Arial-Black-90");
		letter2.setColor(Color.yellow);
		
		letter3 = new GLabel("?",310,360);
		letter3.setFont("Arial-Black-90");
		letter3.setColor(Color.yellow);
		
		letter4 = new GLabel("?",440,360);
		letter4.setFont("Arial-Black-90");
		letter4.setColor(Color.yellow);
		
		letter5 = new GLabel("?",570,360);
		letter5.setFont("Arial-Black-90");
		letter5.setColor(Color.yellow);
		
		letter6 = new GLabel("?",700,360);
		letter6.setFont("Arial-Black-90");
		letter6.setColor(Color.yellow);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		
		//do not list if enter or space or alt is pressed
		if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ALT || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_ESCAPE)
			return;
		
		if(i<6) {
			name[i] = (char)key;
			i++;
		}
		else {
			i=0;
			name[i] = (char)key;
			i++;
		}
	}
	
	@Override
	public void showContents() {
		program.add(screen);
		program.add(letter1);
		program.add(letter2);
		program.add(letter3);
		program.add(letter4);
		program.add(letter5);
		program.add(letter6);
		timer = new Timer(10,this);
		timer.start();
	}

	@Override
	public void hideContents() {
		program.remove(screen);
		program.remove(rect);
		program.remove(letter1);
		program.remove(letter2);
		program.remove(letter3);
		program.remove(letter4);
		program.remove(letter5);
		program.remove(letter6);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			nextPane();
		}
	}
	
	public void nextPane() {
		//sets the player's name switches to the game over screen
		setName();
		program.setName(realName);
		program.switchToGameOver();
	}
	
	//setter
	public void setName() {
		realName = "";
		for(int i = 0; i<6; i++) {
			realName = realName + Character.toString(name[i]);
		}
		System.out.println(realName);
	}
	
	//getter
	public String getName() {
		return realName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(i == 1) {
			letter1.setLabel(Character.toString(name[0]));
		}
		if(i == 2) {
			letter2.setLabel(Character.toString(name[1]));
		}
		if(i == 3) {
			letter3.setLabel(Character.toString(name[2]));
		}
		if(i == 4) {
			letter4.setLabel(Character.toString(name[3]));
		}
		if(i == 5) {
			letter5.setLabel(Character.toString(name[4]));
		}
		if(i == 6) {
			letter6.setLabel(Character.toString(name[5]));
			program.add(rect); //adds the next button once the user has input 6 characters
			i++;
		}
	}
}
