package river.problems.farmer;

import river.Manifest;
import river.Node;
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
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			if (m.size(type) > 0 && 
				m.size(Fox.type) > 0 &&
				m.size(Farmer.type) == 0) {
				return false;
			}
		}
		
		return true;
	}
}