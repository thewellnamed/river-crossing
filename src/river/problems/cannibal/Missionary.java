package river.problems.cannibal;

import river.problems.PassengerType;

/**
 * Missionary Passenger Type
 * Cannot be outnumbered by Cannibals
 */
public class Missionary extends PassengerType {

	/**
	 * Singleton
	 */
	private Missionary() {}
	public static Missionary type = new Missionary();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Missionary";
	}
}
