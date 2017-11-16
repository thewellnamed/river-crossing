package river.problems.cowboys;

import river.problems.PassengerType;

/**
 * Missionary Passenger Type
 * Cannot be outnumbered by Cannibals
 */
public class Cowboy extends PassengerType {

	/**
	 * Singleton
	 */
	private Cowboy() {}
	public static Cowboy type = new Cowboy();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Cowboy";
	}
}
