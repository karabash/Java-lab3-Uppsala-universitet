package game_stenSaxPåse;
import java.util.Random;
// singleton class creates only one object
class Computer  extends Player{
	private static Computer playerIsPc;
	private Random random = new Random();



	// static block initialize directly playerIsOc and Random

	static {
		playerIsPc = new Computer();
	}

	//Constructor
	Computer(){
		//calls the super class
		super();
		// setting a name to computer (player name is computer)
		super.setName("Datorn");
	}
	// getting computer instance
	public static Computer getInstance() {
		return playerIsPc;
	}

	// make a random number
	public int makeARandomNumber(){
		return random.nextInt(3);

	}
	// this method returns random number
	public int computerChoose(int random) {
		// check array index is bigger 0 AND
		// smaller equal 2
		if(random>=0 && random<=2) {
			random = makeARandomNumber();
			System.out.println("Datorn väljer " + Contents.ALTERNATIVES[random] + ".");
			return random;
		}
		// if, if statement does not execute shut down
		else {
			System.out.println("Computer is confused");
			System.exit(0);
		}
		return random;

	}



}
