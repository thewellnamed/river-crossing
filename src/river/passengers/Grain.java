package river.passengers;

import river.Manifest;
import river.Node;

/**
 * Can't be with a fox
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
		return "G";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			if (m.size(type) > 0 && 
				m.size(Fox.type) > 0) {
				return false;
			}
		}
		
		return true;
	}
}