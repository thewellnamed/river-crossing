package river;

/**
 * Cannibal type
 * See Missionary for rules
 */
public class Cannibal extends PassengerType {

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
	public boolean validate(Node state) {
		return true;
	}
}
