package Table;

//Every game has an exit so
//a game has to implement this interface
public interface Exiter {
	// this abstract method returns true
	// and takes an argument as String to
	// to say by user with his/her name
	public abstract boolean isExitDesired(String userName);
}
