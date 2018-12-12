package playerAttributes;

// This interface has to be implement by players 
// because every player has a name
public interface PlayerAttributes {

	// implicitly abstract method (you can think as public)
	//takes one arguments as String
	void setName(String  playerName);

	// implicitly abstract method (you can think as public)
	//this abstract method returns String
	String getName();
}
