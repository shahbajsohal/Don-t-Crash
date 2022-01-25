import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import acm.graphics.GImage;

public class EnemyCar extends Object implements ActionListener{
	private String fileName;
	private int width = 50;
	private int height = 100;
	private double dx;
	private double dy;
	private Timer enemyTimer = new Timer(1000, this); // timer that can/will be adjusted
	private MainApplication program;
	private GImage car;
	
	public void actionPerformed(ActionEvent e) {
		enemyTimer.stop();
	}
	
	public EnemyCar(MainApplication app, PlayPane pane, double dx, double dy, double x, double y) {
		program = app;
		
		this.dx = dx;
		this.dy = Math.abs(dy);
		if(dy < 1)
			this.dy=1;
		
		//randomly chosen car image
		Random rand = new Random();
		int roll = rand.nextInt() % 7; //based on number of cars
		switch(roll) {
			case 0:
				fileName = "AssetImages/Mclaren.png";
				break;
			case 1:
				fileName = "AssetImages/lotus.png";
				break;
			case 2:
				fileName = "AssetImages/Nissan.png";
				break;
			case 3:
				fileName = "AssetImages/BuickerB.png";
				break;
			case 4:
				fileName = "AssetImages/RamB.png";
				break;
			case 5:
				fileName = "AssetImages/Mercedez.png";
				break;
			case 6:
				fileName = "AssetImages/toyota.png";
				break;
			case 7:
				fileName = "AssetImages/Audi.png";
				break;
			default:
				fileName = "AssetImages/Porsche.png";
				break; 
		}
		
		//System.out.println(fileName);
		car = new GImage(fileName,x,y);
		car.setSize(width, height);
	}
	
	//update location
	void update() {
		if(dx!=0) {
			if(dx>0) {
				if(car.getX()+car.getWidth()+dx>650) {
					car.setLocation(650-car.getWidth(),car.getY()+dy);
					dx = dx*-1;
				}
				else {
					car.move(dx, dy);
				}
			}
			else {
				if(car.getX()+dx<150) {
					car.setLocation(150,car.getY()+dy);
					dx = dx*-1;
				}
				else {
					car.move(dx, dy);
				}
			}
		}
		else
			car.move(dx, dy);	
	}

	//getters
	public int getWidth() { 
		return width;
	}
	
	public int getHeight() { 
		return height;
	}
	
	public double getY() {
		return car.getY();
	}
	
	public String getFileName() { 
		return fileName;
	}

	public GImage getImage() {
		return car;
	}
	
	public double getDX() {
		return dx;
	}
	
	//setter
	public void setDX(double d) {
		dx = d;
	}
	
	//show/hide GImage
	public void show() {
		program.add(car); 
	}
	public void hide() {
		program.remove(car);
	}
}
