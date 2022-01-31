
public class CarSelectionPane extends GraphicsPane{
	private MainApplication program;
	private GButton button1;
	private GButton button2;
	
	public CarSelectionPane (MainApplication app) {
		super();
		program = app;
		button1 = new GButton("Next", 600, 200, 100, 100);
		button2 = new GButton("Previous", 50, 200, 100, 100);
	}
	
	//@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(button1);
		program.add(button2);
		
	}

	//@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(button1);
		program.remove(button2);
	}

}
