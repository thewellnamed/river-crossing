package river.problems.cowboys;

import river.Manifest;
import river.Node;
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
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			if (m.size(type) > 0 && 
				m.size(Cowboy.type) < m.size(type)) {
				return false;
			}
		}
		
		return true;
	}
}
