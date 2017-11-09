package river;

import java.util.Stack;

/**
 * Missionary Passenger Type
 * Cannot be outnumbered by Cannibals
 */
public class Missionary implements PassengerType {

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
	public boolean validate(Stack<Node> state, Manifest m) {
		if (m.size(Cannibal.type) > m.size(Missionary.type)) {
			return false;
		}
		
		return true;
	}
}
