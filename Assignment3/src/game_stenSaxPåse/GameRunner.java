package game_stenSaxPåse;
import java.io.IOException;
import java.util.Scanner;

// class name
class GameRunner {
	//All of them creates one object and they are class field can't be access out of class
	private static Contents contents = new Contents();
	private static GameRunner gameRunner = new GameRunner();
	private static Computer computer = Computer.getInstance();
	private static int lap = 5;
	private static GameFile.ScoreWritter scoreWriter; 
	private static Scanner sc = new Scanner(System.in);
	private static GameTable gameTable= new GameTable();
	private String scoreUpdateText;
	private boolean isUserWillingToPlayAgain;

	// getter
	private boolean isUserWillingToPlayAgain() {
		return isUserWillingToPlayAgain;
	}

	// setter
	private void setUserWillingToPlayAgain(boolean isUserWillingToPlayAgain) {
		this.isUserWillingToPlayAgain = isUserWillingToPlayAgain;
	}

	// main method
	public static void main(String[] args) throws IOException   {

		letsPlay();
	}

	// play method
	// this method throws runtime exception 
	//bc of using reader() class. 
	// DONT REMOVE IOEXCEPTION other wise
	// you will trigger "Unhandled exception" at compile time

	private static void letsPlay() throws IOException {
		// ask user name
		gameTable.askName();

		//set player name (GameTableInformation has Player)
		gameTable.setName((sc.nextLine()));

		// greet user with his/hers name
		gameTable.greatUser();

		// local variable
		String text;

		// Create a GameFile instance variable 
		GameFile gameFile = new GameFile();

		// creates a ScoreWritter object (this is an inner class inside the GameFile.java)
		scoreWriter	= gameFile.new ScoreWritter();

		// creates a ScoreReader object (this is an inner class inside the GameFile.java)
		GameFile.ScoreReader scoreReader = gameFile.new ScoreReader();

		// read scores from "Score.txt" file
		scoreReader.reader(gameTable.getPlayer1Name());




		// while user not enter q loop will continue 
		//if ther is winner return false
		// loop until lap bigger than 5 (play 5 times)
		while( !(contents.isItQuit())  & !(contents.isThereWinner()) & lap>0 ){

			//make a choose info
			gameTable.makeChoose();

			//set user input
			contents.setUserInput(contents.getScanner().nextLine().trim());


			// if user enter Q or q return true and execute if statement
			if( gameTable.isExitDesired(contents.getUserInput())) {
				//say by to user
				gameTable.exitMessage(gameTable.getPlayer1Name());
				// exit
				System.exit(0);
			}

			else{

				// until user inputs not same as "sten", "sax", "påse" or "Sten", "Sax", "Påse"
				// continue  looping
				while(!gameRunner.checkUserInputEqualsWithContents()){
					gameTable.invalidValueMessage("\n       --------------------------------------------------------\n"
							+ "      | Ogiltigt värde. Giltiga värden är sten, sax eller påse.|"+
							"\n       ---------------------------------------------------------\n\n");
					//make choose "sten", "sax", "påse"
					gameTable.makeChoose();
					// set user input
					contents.setUserInput(contents.getScanner().nextLine().trim());
					// if user enter Q or q return true and execute if statement
					if(gameTable.isExitDesired(contents.getUserInput())) {
						//say by to user
						gameTable.exitMessage(gameTable.getPlayer1Name());
						System.exit(0);
					}
				}
				// print out the user own choice
				gameTable.printUserChoice(contents.getUserInput());
				gameRunner.updateScoreAndCheck();

			}
			// this variable counts lap
			lap--;

		}
		// shows winener
		gameTable.winnerMessage(contents.getScore()[0],contents.getScore()[1]);

		//if lap equals 0
		if(lap==0) {
			// read "Score.txt file"
			scoreReader.reader(gameTable.getPlayer1Name());
			// set isUserWillingToPlayAgain false
			gameRunner.setUserWillingToPlayAgain(false);
			System.out.println("Vii du spela om igen? Y/N");
			String answer =sc.nextLine();
			// call the checkeAndUpdateScore method. this method updates score and gives info
			playOneMoreTime(answer);
			// isUserWillingToPlayAgain is true play 5 lap again
			while(gameRunner.isUserWillingToPlayAgain) {
				letsPlay();

			}
		}

	}

	private static boolean  playOneMoreTime(String answer) {
		// take user input first character
		gameRunner.setUserWillingToPlayAgain(answer.substring(0,1).equalsIgnoreCase("Y"));

		// if user enter y or Y execute if statement and reset all values
		if((gameRunner.isUserWillingToPlayAgain())){
			lap=5;
		//	playerScore=computerScore=0;
			contents.getScore()[0] = 	contents.getScore()[1]= 0;

			return gameRunner.isUserWillingToPlayAgain;

		}
		// if, if statement return false execute else statement and quit the game
		else {
			gameTable.exitMessage(gameTable.getPlayer1Name());

			System.exit(0);
		}
		// return true or false
		return gameRunner.isUserWillingToPlayAgain();

	}

	// compare user input and alternatives.
	private final boolean checkUserInputEqualsWithContents() {
		return  contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[0]) | 
				contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[1]) |
				contents.getUserInput().equalsIgnoreCase(Contents.ALTERNATIVES[2]);

	}



	private final void updateScoreAndCheck() throws IOException {
		String text = null;
		// get a random number from computer
		int pcIndex = computer.computerChoose(computer.makeARandomNumber());

		// if pc index 0 and user input equals with sax or Sax increase computer score
		if( (pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("sax") |
				// if pc index 1 and user input equals with påse or Påse increase computer score
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("påse") |
				// if pc index 1 and user input equals with sten or Sten increase computer score
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("sten"))))){

			// increase computerScore
		//	int coputerScore = 
					++contents.getScore()[0];

			//return local variable text
			text =  computer.getName() + " score är "+ contents.getScore()[0];
			// write methods 
			gameTable.methodPrinter(Contents.ALTERNATIVES[pcIndex], Contents.METHODS[pcIndex], contents.getUserInput());

			// write computer score into "Score.txt" file
			scoreWriter.writeScore(text+"\n");


		}

		// if user input and pc has same chose 
		else if ((pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("sten") |
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("sax") |
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("påse"))))) {	
			System.out.println("Det är Jämnt!");
			// write  into "Score.txt" file "Det är Jämnt!
			scoreWriter.writeScore("Det är Jämnt!\n");

		}
		// if pc index 0 and user input equals with påse or Påse increase computer score
		else if (pcIndex == 0 & contents.getUserInput().equalsIgnoreCase("påse") |
				// if pc index 0 and user input equals with sten or Sten increase computer score
				(pcIndex == 1 & contents.getUserInput().equalsIgnoreCase("sten") |
				// if pc index 0 and user input equals with sax or Sax increase computer score
				(pcIndex == 2 & contents.getUserInput().equalsIgnoreCase("sax")))){
			int userIndex = 0;
			
			// get user choice index
			for(int i = 0; i<Contents.ALTERNATIVES.length;i++) {
				if( contents.getUserInput().equalsIgnoreCase((Contents.ALTERNATIVES[i]))){
					userIndex = i;
				}
			}
			// Increase playerScore
			++contents.getScore()[1];
					//= ++playerScore;
			text = gameTable.getPlayer1Name()  + " score är "+ contents.getScore()[1];
			
			// write methods 
			gameTable.methodPrinter(Contents.ALTERNATIVES[userIndex], Contents.METHODS[userIndex], Contents.ALTERNATIVES[pcIndex]);
			// write  into "Score.txt"
			scoreWriter.writeScore(text+"\n");
		}
		scoreUpdateText =	gameTable.getGaMeScore(contents.getScore()[0], contents.getScore()[1], gameTable.getPlayer1Name() ,computer.getName());
		// print this lap score
		System.out.print(scoreUpdateText);


	}




}



