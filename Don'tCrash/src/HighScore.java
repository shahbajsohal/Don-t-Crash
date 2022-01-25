import java.io.*;
import java.util.*;

public class HighScore {
/*
 * The constructor gets names and scores in order from a text file and 
 * assigns them to their respective arrays of scores[] and names[].  
 * checkHighScore() is used to compare the user’s score with the lowest 
 * score to see if the player qualifies for a spot on the leaderboard.  
 * newHighScore removes the lowest score in the array and replaces it with 
 * the new score and sorts the array and rewrites the text file with the new scoreboard.
 * 
 * The HighScore.txt file is one continuous string.  Names first, scores second.  Names are
 * a length of 6, scores are a length of 6.
 */
	int scores[];
	String names[];
	String list;
	Scanner reader;
	File data;
	
	//constructor
	public HighScore() {
		scores = new int[10];
		names = new String[10];
		
		//text file contains all of the scores in a single line
		data = new File("HighScores.txt");
		try {
			reader = new Scanner(data);
			list = reader.nextLine();

		    //reads text file and assigns the scores/names to their respective arrays
			//*ascending order*
			//lowest score is index 0
			for(int i = 0; i<10; i++){
				names[i] = list.substring(12*i,12*i+6);
				
			    String sc = list.substring(12*i+6, 12*i+12);
			    int numScore = Integer.parseInt(sc);

			    scores[i] = numScore;
			    
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	}
	
	public boolean checkHighScore(String name, int score) {
		//checks the lowest score to see if the player reached the scoreboard
		if(scores[0]<score)
			return true;
		else
			return false;
	}
	
	public void newHighScore(String name, int score) throws IOException {
		FileWriter fw = new FileWriter(data);
		
		//player replaces the lowest score, and the program sorts the new leaderboard
		if(checkHighScore(name, score)) {
			names[0] = name;
			scores[0] = score;
			
			sort();
		}
		
		//text file is overwritten
		for(int i = 0; i<10; i++) {
			fw.write(names[i]);
			fw.write(Integer.toString(scores[i]));
		}
		fw.close();
	}
	
	//insertion sort
	public void sort() {
		int n = scores.length;  
		
	    for (int j = 1; j < n; j++) {  
	    	int key = scores[j];
	    	String key2 = names[j];
	    	int i = j-1;  
	    	while ( (i > -1) && ( scores[i] > key ) ) {  
	    		scores[i+1] = scores[i];
	    		names[i+1] = names[i];
	    		i--;  
	    	}  
	    	scores[i+1] = key;
	    	names[i+1] = key2;
	    }
	}
	
	public void print() {
		for(int i = 9; i>=0; i--) {
			System.out.println("Name: "+names[i]+" Score: "+scores[i]);
		}
		System.out.println("");
	}
	
	public String[] getNames() {
		return names;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public static void main(String args[]) {
		HighScore o = new HighScore();
		
		o.print();
		
		try {
			o.newHighScore("Dababy", 999999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("DIDNT WORK");
		}
		
		System.out.println("INSERTING: Dababy, 999999");
		o.print();
	}
}
