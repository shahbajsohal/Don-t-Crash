	import java.io.IOException;

import acm.graphics.GImage;

public class PlayerCar extends Object{
	private String fileName;
	private int width;
	private int height;
	private double x;
	private double y;
	private double dx;
	private int health;
	private MainApplication program;
	private PlayPane game;
	private GImage car;
	
	public PlayerCar(MainApplication app, PlayPane pane){ 
		x = 400;
		y = 450;
		dx = 0;
		program = app;
		car = new GImage("AssetImages/SuperB.png",x,y);
		car.setSize(60,120);
	}
	
	public void setPlayerCar() {
		//car = new GImage("AssetImages/SuperB.png",x,y);
		car.setImage(program.getCarSelection());
		car.setSize(60,120);
	}
	
	public void updateDX(int x) {
		if(x < 0 && dx > 0)
			dx = -5;
		else if(x > 0 && dx < 0)
			dx = 5;
		else if((x >= 0 && dx <= 0) || (x <= 0 && dx >= 0))
			dx += x;
	}
	
	public void stopDX() {
		if(dx<0) {
			while(dx!=0)
				dx++;
		}
		else if(dx>0) {
			while(dx!=0)
				dx--;
		}
	}
	
	public void update() {
		if(car.getX() + dx < 150) {
			dx = 0;
		}
		else if(car.getX()+dx > 650-car.getWidth()) {
			dx = 0;
		}
		car.move(dx, 0);
	}
	
	public void show() {
		program.add(car);
	}
	public void hide() {
		program.remove(car);
	}
	
	//getters
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public String getFileName() {
		return fileName;
	}
	
	public GImage getImage() {
		return car;
	}
	
	public int getHealth() {
		return health;
	}
}
