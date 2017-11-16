package river.problems;

import java.util.Objects;

import river.Node;

/**
 * PassengerType base class
 */
public abstract class PassengerType {
	private int hash = 0;
	
	public abstract String getName();
	public abstract boolean validate(Node state);
	
	/**
	 * Treat separate PassengerType instances as equivalent
	 * if they have the same name
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof PassengerType)) return false;
		
		PassengerType pt = (PassengerType) o;
		return pt.getName().equals(getName());
	}
	
	@Override
	public int hashCode() {
		if (hash == 0) {
			hash = Objects.hash(getName());
		}
		
		return hash;
	}
}
