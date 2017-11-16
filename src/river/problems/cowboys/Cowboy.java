package river.problems.cowboys;

import river.Manifest;
import river.Node;
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
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			if (m.size(type) > 0 && 
				m.size(Mexican.type) + m.size(Indian.type) < m.size(type)) {
				return false;
			}
		}
		
		return true;
	}
}
