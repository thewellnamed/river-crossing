package river.problems.cowboys;

import river.problems.PassengerType;

/**
 * Missionary Passenger Type
 * Cannot be outnumbered by Cannibals
 */
public class Mexican extends PassengerType {

	/**
	 * Singleton
	 */
	private Mexican() {}
	public static Mexican type = new Mexican();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Mexican";
	}
}
