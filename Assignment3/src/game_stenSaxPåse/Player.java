package game_stenSaxPåse;

import playerAttributes.PlayerAttributes;

class Player implements PlayerAttributes{
	private String name;
	
	//change to private with getter and setter
	static int playerTotalScore;
	
	// set name
	@Override
	public void setName(String name) {
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	// get name
	@Override
	public String getName() {
		return this.name;
	}
	// to string method returns to human readable text that is name in this scenario
	public String toString() {
	  return this.getName();
	}
	
}
