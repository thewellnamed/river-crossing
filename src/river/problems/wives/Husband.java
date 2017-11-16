package river.problems.wives;

import river.Node;
import river.problems.PassengerType;

public class Husband extends PassengerType {
	private int id;
	
	public Husband(int num) {
		id = num;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return String.format("H%s", id);
	}
	
	public boolean validate(Node n) {
		return true;
	}	
}
