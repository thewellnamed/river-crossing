package river.problems.farmer;

import river.problems.PassengerType;

/**
 * Farmer has to be in the boat when it is crossing
 */
public class Farmer extends PassengerType {
	/**
	 * Singleton
	 */
	private Farmer() {}
	public static Farmer type = new Farmer();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Farmer";
	}
}
