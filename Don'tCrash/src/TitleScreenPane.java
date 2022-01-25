import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class TitleScreenPane extends GraphicsPane {
	private MainApplication program; 
	private GImage tScreen;
	private GButton button;
	
	public TitleScreenPane(MainApplication app) {
		super();
		program = app;
		button =  new GButton("", 240, 510, 335, 55);
		button.setFillColor(Color.red);
		button.setVisible(false);
		
		tScreen = new GImage("AssetImages/title screen.png", 0,0);
		tScreen.setSize(800,600);
	}

	//@Override
	public void showContents() {
		program.add(tScreen);
		program.add(button);
	}

	//@Override
	public void hideContents() {
		program.remove(tScreen);
		program.remove(button);
	}

	//@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == button) {
			program.switchToMenu();
		}
	}
}
