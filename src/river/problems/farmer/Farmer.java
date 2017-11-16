package river.problems.farmer;

import river.Node;
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
		return "F";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Node n) {
		if ((n.state == Node.TRAVEL_LEFT || n.state == Node.TRAVEL_RIGHT) &&
			n.boat.size(type) == 0) {
			return false;
		}
		
		return true;
	}
}
