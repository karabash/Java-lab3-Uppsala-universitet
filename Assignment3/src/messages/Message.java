package messages;

public interface Message {
	public void invalidValueMessage();
	public void winnerMessage(int pcScore, int playerScore);
	public	void tieMessage();
	
	 public void exitMessage(String name);

}
