package river;

import java.util.HashMap;
import java.util.Set;

import river.passengers.PassengerType;

/**
 * A Passenger Manifest
 */
public class Manifest {
	private HashMap<PassengerType, Integer> passengers;
	private int size;
	
	/**
	 * Construct
	 */
	public Manifest() {
		passengers = new HashMap<PassengerType, Integer>();
		size = 0;
	}
	
	/**
	 * Add Passengers
	 * @param PassengerType type
	 * @param Integer count
	 */
	public void add(PassengerType type, Integer count) {
		if (count <= 0) return;
		
		if (passengers.containsKey(type)) {
			passengers.put(type, passengers.get(type) + count);
		} else {
			passengers.put(type,  count);
		}
		
		size += count;
	}
	
	/**
	 * Copy passengers from existing Manifest
	 * @param m Manifest
	 */
	public void add(Manifest m) {
		for (PassengerType t : m.passengerTypes()) {
			add(t, m.size(t));
		}
	}
	
	/**
	 * Remove passengers
	 * @param PassengerType type
	 * @param Integer count
	 */
	public void remove(PassengerType type, Integer count) {
		if (count <= 0) return;
		
		if (passengers.containsKey(type)) {
			int current = passengers.get(type);
			if (current < count) {
				throw new IllegalStateException(
					String.format("trying to remove more passengers than currently in manifest! Current=%d, removing=%d", current, count));
			}
			
			if (current - count == 0) {
				passengers.remove(type);
			} else {
				passengers.put(type, current - count);
			}
			
			size -= count;
		}
	}
	
	/**
	 * Remove passengers specified in other manifest
	 * @param Manifest m
	 */
	public void remove(Manifest m) {
		for (PassengerType t : m.passengerTypes()) {
			remove(t, m.size(t));
		}
	}
	
	/**
	 * Total size of Manifest
	 * @return int
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Number of type PassengerType
	 * @param PassengerType type
	 * @return int
	 */
	public int size(PassengerType type) {
		Integer ret = passengers.get(type);
		return (ret == null) ? 0 : ret;
	}
		
	/**
	 * Get list of passenger types in Manifest
	 * @return Set<PassengerType>
	 */
	public Set<PassengerType> passengerTypes() {
		return passengers.keySet();
	}
	
	/**
	 * Copy
	 */
	public Manifest clone() {
		Manifest clone = new Manifest();
		for (PassengerType type : passengers.keySet()) {
			clone.add(type, passengers.get(type));
		}
		
		return clone;
	}
	
	/**
	 * Overrides for HashSet lookups
	 */
	@Override 
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Manifest)) return false;
		
		Manifest m = (Manifest) o;
		return m.passengers.equals(passengers);
	}
	
	@Override
	public int hashCode() {
		return passengers.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("(");
		for (PassengerType type: passengers.keySet()) {
			if (s.length() > 1) s.append(", ");
			s.append(type.getName());
			s.append("=");
			s.append(size(type));
		}
		s.append(")");
		
		return s.toString();
	}
}
