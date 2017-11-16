package river.problems.farmer;

import river.Node;
import river.problems.PassengerType;

/**
 * Can't be with a fox
 */
public class Fox extends PassengerType {
	/**
	 * Singleton
	 */
	private Fox() {}
	public static Fox type = new Fox();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "X";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		return true;
	}
}
