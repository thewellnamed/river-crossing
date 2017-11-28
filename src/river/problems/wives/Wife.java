package river.problems.wives;

import river.problems.PassengerType;

public class Wife extends PassengerType {
	private int id;
	
	public Wife(int num) {
		id = num;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return String.format("W%s", id);
	}	
}