package messages;

// this interface contains game messages
public interface Message {
	// abstract method
	public abstract void invalidValueMessage();
	// abstract method
	public abstract void winnerMessage(int pcScore, int playerScore);
	// abstract method
	public	abstract void tieMessage();
	// abstract method
	public abstract void exitMessage(String name);

}

