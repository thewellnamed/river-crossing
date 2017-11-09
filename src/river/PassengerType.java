package river;

/**
 * PassengerType base class
 */
public abstract class PassengerType {
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
}
