package river;

import java.util.HashSet;
import java.util.Objects;

import river.passengers.PassengerType;

/**
 * Game state node
 * 
 * Stores the current state of a crossing in progress
 * - state: The location of the boat (left, right, traveling right, traveling left)
 * - Passenger Manifest for (left, right, boat)
 */
public class Node {
	public static final int BOAT_LEFT = 0;
	public static final int TRAVEL_RIGHT = 1;
	public static final int BOAT_RIGHT = 2;
	public static final int TRAVEL_LEFT = 3;
	
	public Manifest left;
	public Manifest right;
	public Manifest boat;
	public int state;
	
	/**
	 * Construct
	 */
	public Node(int currentState, Manifest l, Manifest r, Manifest b) {
		left = l;
		right = r;
		boat = b;
		state = currentState;
	}
	
	/**
	 * Check validity by calling into rules for each passenger type
	 * @return boolean
	 */
	public boolean isValid() {
		boolean valid;
		
		HashSet<PassengerType> types = new HashSet<PassengerType>();
		types.addAll(left.passengerTypes());
		types.addAll(right.passengerTypes());
		types.addAll(boat.passengerTypes());

		for (PassengerType type : types) {
			valid = type.validate(this);
			
			if (!valid) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Overrides for HashMap lookups
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Node)) return false;
		
		Node n = (Node) o;
		return (n.state == state &&
				n.left.equals(left) &&
				n.right.equals(right) &&
				n.boat.equals(boat));
	}
	
	@Override
	public String toString() {
		return String.format("Node(%s) STATE=%s left=%s, boat=%s, right=%s", hashCode(), getStateString(), left, boat, right);
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(state, left, right, boat);
	}
	
	/**
	 * Prettier toString()
	 * @return String
	 */
	public String prettyString() {
		return String.format("%s [Left%2$-20s    Boat%3$-20s    Right%4$-20s]", getStateString(), left, boat, right);
	}
	
	/**
	 * State label
	 * @return String
	 */
	private String getStateString() {
		String[] labels = { "LEFT ", " --> ", "RIGHT", " <-- " };
		return labels[state];
	}
}