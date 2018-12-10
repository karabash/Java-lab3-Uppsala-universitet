package game_stenSaxPåse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Contents {
	private final int[] score = new int[2];
	private boolean isItQuit = false;
	private boolean isThereWinner = false;
	private Scanner scanner = new Scanner(System.in);
	private String userInput;

	static final String[] ALTERNATIVES = {"sten", "sax", "påse"};
	static final String[] METHODS = {"krossar", "klipper", "fångar"};
	private static final String QUIT = "Q";
    static final int scoreIncreaser=1; 

    
    
   
	public String getQuit() {
		return QUIT;
	}

	public String getUserInput() {
		return userInput;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public boolean isItQuit() {
		return isItQuit;
	}

	public void setItQuit(boolean isItQuit) {
		this.isItQuit = isItQuit;
	}

	public void setUserInput(String userInput) {
		if(!userInput.equals(""))
		this.userInput = userInput;
		else
			this.userInput = "a";
	}

	public boolean isThereWinner() {
		return isThereWinner;
	}

	public void setThereWinner(boolean isThereWinner) {
		this.isThereWinner = isThereWinner;
	}
	
	public void setScore(int index) {
	  if(index<2 && index>0)
		this.score[index] = index;
	}


	public int[] getScore() {
		return score;
	}

	



	

}
