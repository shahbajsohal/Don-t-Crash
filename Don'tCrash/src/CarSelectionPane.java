import java.awt.event.MouseEvent;
import java.io.IOException;

import acm.graphics.GObject;

public class CarSelectionPane extends GraphicsPane{
	private MainApplication program;
	private GButton next;
	private GButton previous;
	private ImageArray carArr;
	
	public CarSelectionPane (MainApplication app) throws IOException {
		super();
		program = app;
		carArr = new ImageArray("cars.txt", program);
		carArr.show();
		next = new GButton("Next", 600, 200, 100, 100);
		previous = new GButton("Previous", 50, 200, 100, 100);
	}
	
	//@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(next);
		program.add(previous);
		
	}

	//@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(next);
		program.remove(previous);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == next) {
			carArr.next();
		}
		else if (obj == previous) {
			carArr.previous();
		}
	}
}
