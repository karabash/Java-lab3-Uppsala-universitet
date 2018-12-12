package Table;
@FunctionalInterface
//Every game has a greeting so
//a game has to implement this interface
public interface PlayerGreeting {
	// this abstract method returns player name
	// and takes no argument.
	// Purpuse say welcome to user with with his/her name
	String greatingUser();

}
