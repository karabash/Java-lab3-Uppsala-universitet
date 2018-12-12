package messages;

// this interface contains game messages
public interface Message {
	
	// abstract method
	public abstract void winnerMessage(int pcScore, int playerScore);
	
	// abstract method
	public	abstract void tieMessage();
	
	// abstract method
	void giveGameExitInfo(String playerName);

	// abstract method
	public void invalidValueMessage(String invalidmessageType);
	
	// abstract method
	public void  exitMessage(String textAfterUserEnterQ);
}
