import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.GImage;


public class ImageArray {
	private MainApplication program;
	int currCar ;
	ArrayList<GImage> playerCars;
	private String[] fullFile;
	
	public ImageArray(String path, MainApplication program) throws IOException {
		this.program = program;
		currCar = 0;
		String file = textToString(path);
		fullFile = file.split("\\s+");
		int numOfCars = Integer.parseInt(fullFile[0]);
		
		playerCars = new ArrayList<GImage>();
		
		for(int i = 0; i < numOfCars; i++) {
			GImage car = new GImage(fullFile[i+1], 300, 250);
			car.setSize(150, 200);
			playerCars.add(car);
		}
	}
	public void show() {
		program.add(playerCars.get(currCar));
		
	}
	
	public void hide() {
		program.remove(playerCars.get(currCar));
	}
	public String get() {
		return fullFile[currCar+1];
	}
	public void next() {
		hide();
		currCar++;
		if(currCar == 3) {
			currCar = 0;
		}
		show();
	}
	
	public void previous() {
		hide();
		currCar--;
		if(currCar == -1) {
			currCar = 2;
		}
		show();
	}
	public String textToString(String path) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
}
