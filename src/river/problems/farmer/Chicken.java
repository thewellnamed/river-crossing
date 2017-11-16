package river.problems.farmer;

import river.problems.PassengerType;

/**
 * Can't be alone with a fox
 * Without the farmer
 */
public class Chicken extends PassengerType {
	/**
	 * Singleton
	 */
	private Chicken() {}
	public static Chicken type = new Chicken();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Chicken";
	}
}
