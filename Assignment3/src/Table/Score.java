package Table;
// THIS IS AN FUNCTIONAL INTERFACE 
// YOU CAN ADD STATIC OR DEFAULT METHOD BUT NOT 
// AN OTHER abstract METHOD otherwise you accuse a compiler error as
//"The target type of this expression must be a functional interface compiler error!"

@FunctionalInterface
// Every game has a score so a game should implement this @FunctionalInterface

public interface Score {
	// the getScore takes 3 arguments int computerScore, int playerScore,
	// playerName  and returns to String to print out score
  String getScore(int computerScore, int playerScore, String playerName );
}
