package game_stenSaxPåse;
import Table.Exiter;
import Table.PlayerGreeting;
import Table.Score;
import messages.Message;

public class GameTable implements Exiter, Message{
	private Player player1 = new Player(); 
	private static String player1NameHolder="Player";

	 void askName() {
		System.out.println("Vad heter du?");
	}
	
	 String setName(String name){
		try{
			if(name == null || name.length() == 0 ) {
				player1.setName(player1NameHolder);
				System.out.println(this.getPlayer1Name());
				return player1.getName();
			}


			else {
				player1.setName(name);
				return player1.getName();
			}


		}catch(StringIndexOutOfBoundsException e) {
			System.out.println("Invalid name use character! - Run the game agin!");
			System.exit(0);

		}
		return player1.getName();
	}

	public String getPlayer1Name() {
		return this.player1.getName();
	}

	
	
	 void greatUser() {
		PlayerGreeting g = p -> getPlayer1Name();
		
			System.out.print("** HEJ OCH VÄLKOMMEN "+ g.greatingUser((getPlayer1Name())) + " TILL MIN SPEL STEN-SAX-PÅSE ** \nSpelet där sten krossar sax,"
					+ " sax klipper påse och påse fångar sten. Bäst av fem vinner.\n");
			this.giveGameExitInfo(getPlayer1Name());
		}
	
	
	 String getScore(int computerScore, int playerScore, String playerName, String computerName) {
	final	String poäng = "'s poäng är ";
	

	Score	sc = (cS,pS, pN) ->  {return  ("\nGame Score: ").concat(playerName + poäng + playerScore +". "
			+computerName +poäng+ computerScore+"\n");};
		sc.getScore(computerScore, playerScore, playerName).length();
		return sc.getScore(computerScore, playerScore, playerName);
	      
	 		 	 
	}
	 void giveGameExitInfo(String playerName) {
		System.out.println( "Förresten vill du  avsluta programmet "+ playerName +", kan du göra det genom att skriva Q.");
		}

	@Override
	public boolean isExitDesired(String userInput) {
		return userInput.substring(0, 1).equalsIgnoreCase("q"); 		
	}

	@Override
	public void exitMessage(String name) {
		System.out.println("Ha en trevlig dag "+ name);
		
	}

	@Override
	public void invalidValueMessage() {
		System.out.println("Ogiltigt värde. Giltiga värden är sten, sax eller påse.\n");
		
	}

	@Override
	public void winnerMessage(int pcScore, int playerScore) {
		if(playerScore>pcScore)
			System.out.println("winner is "+ getPlayer1Name() + playerScore);
			else if(pcScore>playerScore)
				System.out.println("winner is Datorn " + pcScore);		
			else
				System.out.println("Tie " + pcScore);		

	}

	@Override
	public void tieMessage() {
		System.out.println("There is no winner ");		
	}
	
	 String methodPrinter(String winnerChoice,  String methodName, String looserChoice) {
		String result = winnerChoice + " " + methodName + " "+ looserChoice;
		System.out.print(result + " ");
		return result;
	}                       

	 void printUserChoice(String userInput) {
		System.out.println("Ditt val är "+ userInput);
	}

	 void makeChoose() {
		System.out.println(player1.getName() + " gör ditt val (sten, sax eller påse): ");
	}
	
}
