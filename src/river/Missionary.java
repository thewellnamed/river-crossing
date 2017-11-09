package river;

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
		return "M";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			if (m.size(type) > 0 && 
				m.size(Cannibal.type) > m.size(type)) {
				return false;
			}
		}
		
		return true;
	}
}
