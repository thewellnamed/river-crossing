package river.problems.farmer;

import river.problems.PassengerType;

/**
 * Can't be alone with a Chicken
 * Without the farmer
 */
public class Grain extends PassengerType {
	/**
	 * Singleton
	 */
	private Grain() {}
	public static Grain type = new Grain();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Grain";
	}
}