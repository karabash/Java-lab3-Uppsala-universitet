package Table;
@FunctionalInterface
//Every game has a greeting so
//a game has to implement this interface
public interface PlayerGreeting {
	// this abstract method returns player name
	// and takes an argument as String to
	// to say welcome with user with his/her name
	String greatingUser(String  playerName);

}
