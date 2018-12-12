package game_stenSaxPåse;
import Table.Exiter;
import Table.PlayerGreeting;
import Table.Score;
import messages.Message;
// This class is public and accessible from every package and classes.
// This class implements Exiter and  Message interface
public class GameTable implements Exiter, Message{
	//Table has a player
	private Player player1 = new Player(); 

	// This variable helps to give name as "Player"
	// if a user does not enter his/hers name
	private static String player1NameHolder="Player";

	// ask user name
	// this method does not return anything and does not take arguments
	public void askName() {
		System.out.println("Vad heter du?");
	}

	// Set player name  
	// this methods make unable to direct access to user properties
	//through has - a relationship
	String setName(String name){
		try{
			// the if condition checks a user does  
			if(name == null || name.length() == 0 ) {
				player1.setName(player1NameHolder);
				System.out.println(this.getPlayer1Name());
				return player1.getName();
			}
			else {
				player1.setName(name);
				return player1.getName();
			}

            // catch String exception (length)
		}catch(StringIndexOutOfBoundsException e) {
			// give info to user
			System.out.println("Invalid name use character! - Run the game agin!");
			// quit
			System.exit(0);

		}
		// return playerName
		return player1.getName();
	}

	// player1 instance variable is private 
	// so out side of the class can't access 
	// instance variable attributes therefore vi great this method
	// to get userName 
	public String getPlayer1Name() {
		return this.player1.getName();
	}


    
	void greatUser() {
		// @@FunctionalInterface method greatingUser take an argument
		// and vi pass into argument getPlayer1Name() that returns String
		PlayerGreeting g = () -> getPlayer1Name();
                                                  //calling method
		System.out.print("** HEJ OCH VÄLKOMMEN "+ g.greatingUser() + " TILL MIN SPEL STEN-SAX-PÅSE ** \nSpelet där sten krossar sax,"
				+ " sax klipper påse och påse fångar sten. Bäst av fem vinner.\n");
		this.giveGameExitInfo(getPlayer1Name());
	}


	String getGaMeScore(int computerScore, int playerScore, String playerName, String computerName) {
		final	String poäng = "'s poäng är ";

               // takes 3 arguments   //  getScore() returns String
		Score	sc = (cS,pS, pN) ->  {return  ("\nGame Score: ").concat(playerName + poäng + playerScore +". "
				+computerName +poäng+ computerScore+"\n");};
				// call sc.getScore and that returns text
				return sc.getScore(computerScore, playerScore, playerName);


	}
	// gives info to user how to quit 
	public void giveGameExitInfo(String playerName) {
		System.out.println( "Förresten vill du  avsluta programmet "+ playerName +", kan du göra det genom att skriva Q.");
	}

	@Override
	//take user input first letter and if it is q or Q exit from game
	public boolean isExitDesired(String userInput) {
		return userInput.substring(0, 1).equalsIgnoreCase("q"); 		
	}

	@Override
	// after user enter q or Q write message
	public void exitMessage(String name) {
		System.out.println("Ha en trevlig dag "+ name);

	}

	@Override
	// this method take one argument (hard - coded) that helps to programmer 
	// to give different type invalid messages to user
	public void invalidValueMessage(String invalidmessageType) {
		System.out.print(invalidmessageType);

	}
   // winner
	@Override
	public void winnerMessage(int pcScore, int playerScore) {
		//if player score is greater than computer winner is player
		if(playerScore>pcScore)
			System.out.println("winner is "+ getPlayer1Name() + playerScore);
		//if pcScore  is greater than playerScore winner is datorn
		else if(pcScore>playerScore)
			System.out.println("winner is Datorn " + pcScore);		
		// Else nobody wins
		else
			System.out.println("Ha Ha Ha Ingen vinner " );		

	}
    // tie 
	@Override
	public void tieMessage() {
		System.out.println("There is no winner ");		
	}
  // this method give info about who's choose more powerful 
	String methodPrinter(String winnerChoice,  String methodName, String looserChoice) {
		String result = winnerChoice + " " + methodName + " "+ looserChoice;
		System.out.print(result + " ");
		return result;
	}                       
   //prints user chooes 
	void printUserChoice(String userInput) {
		System.out.println("Ditt val är "+ userInput);
	}
// make a choose
	void makeChoose() {
		System.out.println(player1.getName() + " gör ditt val (sten, sax eller påse):\n");
	}

}
