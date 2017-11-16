package river.problems.wives;

import java.util.Set;

import river.Manifest;
import river.Node;
import river.problems.PassengerType;

public class Wife extends PassengerType {
	private int id;
	
	public Wife(int num) {
		id = num;
	}
	
	public String getName() {
		return String.format("W%s", id);
	}
	
	public boolean validate(Node state) {
		Manifest[] manifests = { state.left, state.right, state.boat };
		Husband myHusband = new Husband(id);
		
		for (Manifest m : manifests) {
			Set<PassengerType> types = m.passengerTypes();
			boolean haveOtherHusbands = false, 
					haveMyHusband = (m.size(myHusband) > 0), 
					haveMe = (m.size(this) > 0);
			
			for (PassengerType p : types) {
				if (p instanceof Husband) {
					Husband h = (Husband) p;
					if (h.getId() != id) {
						haveOtherHusbands = true;
						break;
					}
				}
			}
			
			if (haveMe && haveOtherHusbands && !haveMyHusband) {
				return false;
			}
		}
		
		return true;
	}	
}