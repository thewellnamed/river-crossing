package river.problems.cowboys;

import river.Node;
import river.problems.PassengerType;

/**
 * Missionary Passenger Type
 * Cannot be outnumbered by Cannibals
 */
public class Indian extends PassengerType {

	/**
	 * Singleton
	 */
	private Indian() {}
	public static Indian type = new Indian();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "Indian";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		
		return true;
	}
}
