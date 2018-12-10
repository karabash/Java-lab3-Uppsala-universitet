package game_stenSaxPåse;
// import IOException class inside the io package
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
// import Scanner inside the util package
import java.util.Scanner;

// class name

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;



import Table.PlayerGreeting;


class GameRunner {
	//All of them creates one object and they are class field can't be access out of class
	private static Contents contents = new Contents();
	private static GameRunner gameRunner = new GameRunner();
	private static Computer computer = new Computer();
	private static int lap = 5;
	private static int playerScore = 0;
	private static int computerScore=0;
	private static GameFile.ScoreWritter scoreWriter; 
	private static Scanner sc = new Scanner(System.in);
	private static GameTable gameTable= new GameTable();
	private String scoreUpdateText;
	private boolean isUserWillingToPlayAgain;
	//main method
	private boolean isUserWillingToPlayAgain() {
		return isUserWillingToPlayAgain;
	}


	private void setUserWillingToPlayAgain(boolean isUserWillingToPlayAgain) {
		this.isUserWillingToPlayAgain = isUserWillingToPlayAgain;
	}


	public static void main(String[] args) throws IOException   {
		
		letsPlay();
	}
	
	private static void letsPlay() throws IOException {
		gameTable.askName();
		// get input from user(player)
		//set player name (GameTableInformation has Player)
		gameTable.setName((sc.nextLine()));
		gameTable.greatUser();
		// close scanner
		// local variable
		String text;
		// Create a GameFile instance variable 
		GameFile gameFile = new GameFile();
		// creates a ScoreWritter object (this is an inner class inside the GameFile.java)
		scoreWriter	= gameFile.new ScoreWritter();

		// creates a ScoreReader object (this is an inner class inside the GameFile.java)
		GameFile.ScoreReader scoreReader = gameFile.new ScoreReader();
	

		scoreReader.reader(gameTable.getPlayer1Name());





		while( !(contents.isItQuit())  & !(contents.isThereWinner()) & lap>0 ){
			//make a choose info
			gameTable.makeChoose();

			contents.setUserInput(contents.getScanner().nextLine().trim());
			//TODO


			if( gameTable.isExitDesired(contents.getUserInput())) {
				gameTable.exitMessage(gameTable.getPlayer1Name());
				System.exit(0);
			}

			else{


				while(!gameRunner.checkUserInputEqualsWithContents()){
					gameTable.invalidValueMessage();
					gameTable.makeChoose();
					contents.setUserInput(contents.getScanner().nextLine().trim());
					if(gameTable.isExitDesired(contents.getUserInput())) {
						gameTable.exitMessage(gameTable.getPlayer1Name());
						System.exit(1);
					}
				}
				gameTable.printUserChoice(contents.getUserInput());
				gameRunner.play();

			}
			lap--;

		}
		gameTable.winnerMessage(contents.getScore()[0],contents.getScore()[1]);

		if(lap==0) {
			scoreReader.reader(gameTable.getPlayer1Name());
			gameRunner.setUserWillingToPlayAgain(false);
			System.out.println("Vii du spela om igen? Y/N");
			String answer =sc.nextLine();
			playAgain(answer);
			
			while(gameRunner.isUserWillingToPlayAgain) {
				letsPlay();

			}
		}

	}
	
	private static boolean  playAgain(String answer) {
		gameRunner.setUserWillingToPlayAgain(answer.substring(0,1).equalsIgnoreCase("Y"));
			if((gameRunner.isUserWillingToPlayAgain())){
				lap=5;
				playerScore=computerScore=0;
				contents.getScore()[0] = 	contents.getScore()[1]= 0;

				return gameRunner.isUserWillingToPlayAgain;
		
			}
			else {
				gameTable.exitMessage(gameTable.getPlayer1Name());
				
				System.exit(0);
			}
			return gameRunner.isUserWillingToPlayAgain();

		}

	private final boolean checkUserInputEqualsWithContents() {
		return  contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[0]) | 
				contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[1]) |
				contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[2]);

	}
 


	private final void play() throws IOException {
		String text = null;

		int pcIndex = computer.computerChoose(computer.makeARandomNumber());


		if( (pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("sax") |
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("påse") |
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("sten"))))){

			int coputerScore = ++contents.getScore()[0];
			// Score interface
			text =  computer.getName() + " score är "+ coputerScore;

			gameTable.methodPrinter(Contents.ALTERNATIVES[pcIndex], Contents.METHODS[pcIndex], contents.getUserInput());


			scoreWriter.writeScore(text+"\n");


		}


		else if ((pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("sten") |
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("sax") |
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("påse"))))) {	
			System.out.println("Det är Jämnt!");
			scoreWriter.writeScore("Det är Jämnt!\n");

		}

		else if (pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("påse") |
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("sten") |
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("sax")))){
			int userIndex = 0;
			for(int i = 0; i<Contents.ALTERNATIVES.length;i++) {
				if( contents.getUserInput().equalsIgnoreCase((Contents.ALTERNATIVES[i]))){
					userIndex = i;
				}
			}
			contents.getScore()[1] = ++playerScore;
			text = gameTable.getPlayer1Name()  + " score är "+ playerScore;

			gameTable.methodPrinter(Contents.ALTERNATIVES[userIndex], Contents.METHODS[userIndex], Contents.ALTERNATIVES[pcIndex]);
			scoreWriter.writeScore(text+"\n");


		}

		scoreUpdateText =	gameTable.getScore(contents.getScore()[0], contents.getScore()[1], gameTable.getPlayer1Name() ,computer.getName());
		System.out.print(scoreUpdateText);
		
		
	}

	


}



