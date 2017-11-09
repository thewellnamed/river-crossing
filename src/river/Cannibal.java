package river;

import java.util.Stack;

/**
 * Cannibal type
 * See Missionary for rules
 */
public class Cannibal implements PassengerType {

	/**
	 * Singleton
	 */
	private Cannibal() {}
	public static Cannibal type = new Cannibal();
	
	/**
	 * Passenger type string
	 */
	public String getName() {
		return "C";
	}
	
	/**
	 * Validate
	 */
	public boolean validate(Stack<Node> state, Manifest m) {
		return true;
	}
}
