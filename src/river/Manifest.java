package river;

import java.util.HashMap;
import java.util.Set;

public class Manifest {
	private HashMap<PassengerType, Integer> passengers;
	private int size;
	
	public Manifest() {
		passengers = new HashMap<PassengerType, Integer>();
		size = 0;
	}
	
	public void add(PassengerType type, Integer count) {
		if (count <= 0) return;
		
		if (passengers.containsKey(type)) {
			passengers.put(type, passengers.get(type) + count);
		} else {
			passengers.put(type,  count);
		}
		
		size += count;
	}
	
	public void add(Manifest m) {
		for (PassengerType t : m.passengerTypes()) {
			add(t, m.size(t));
		}
	}
	
	public void remove(PassengerType type, Integer count) {
		if (count <= 0) return;
		
		if (passengers.containsKey(type)) {
			int current = passengers.get(type);
			if (current < count) {
				throw new IllegalStateException(
					String.format("trying to remove more passengers than currently in manifest! Current=%d, removing=%d", current, count));
			}
			
			passengers.put(type, current - count);
			size -= count;
		}
	}
	
	public void remove(Manifest m) {
		for (PassengerType t : m.passengerTypes()) {
			remove(t, m.size(t));
		}
	}
	
	public int size() {
		return size;
	}
	
	public int size(PassengerType type) {
		Integer ret = passengers.get(type);
		return (ret == null) ? 0 : ret;
	}
	
	public boolean isValid() {
		boolean valid;
		
		for (PassengerType type : passengers.keySet()) {
			valid = type.validate(this);
			
			if (!valid) {
				return false;
			}
		}
		
		return true;
	}
	
	public Set<PassengerType> passengerTypes() {
		return passengers.keySet();
	}
	
	public Manifest clone() {
		Manifest clone = new Manifest();
		for (PassengerType type : passengers.keySet()) {
			clone.add(type, passengers.get(type));
		}
		
		return clone;
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
