package game_stenSaxPåse;

import playerAttributes.PlayerAttributes;

// The player class is default access and implements PlayerAttributes
class Player implements PlayerAttributes{
	// the instance variable has to be declared 
	//other wise it will gives compiler error as
	// argument (name) cannot be resolved or is not a field

	private String name;
	
	//overrides setName method
	@Override
	public void setName(String name) {
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	//overrides getName method
	@Override
	public String getName() {
		return this.name;
	}
	// to string method returns to human readable text that is name in this scenario
	public String toString() {
	  return this.getName();
	}
	
}
