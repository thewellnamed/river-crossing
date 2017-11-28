package river.problems.torch;

import java.util.Set;
import java.util.Stack;

import river.Manifest;
import river.Node;
import river.problems.PassengerType;
import river.problems.ProblemDefinition;

/**
 * Torch Crossing
 * 
 * There are four people who must cross, 2 at a time
 * Each takes a variable amount of time (1 min, 2 min, 5 min, 8 min)
 * After each crossing someone must return with the torch (like the boat)
 * Can they each cross in a total of 15 minutes or less?
 */
public class Definition implements ProblemDefinition {
	public Manifest getInitialState() {
		Manifest m = new Manifest();
		
		m.add(new TorchBearer(1), 1);
		m.add(new TorchBearer(2), 1);
		m.add(new TorchBearer(5), 1);
		m.add(new TorchBearer(8), 1);
	    return m;
	}
	
	public int getBoatSize() { 
		return 2;
	}
	
	public String getDescription() {
		return "Torch light crossing";
	}
	
	public boolean validate(Stack<Node> path) {
		int totalTime = 0;
		
		for (Node n : path) {
			if (n.state == Node.TRAVEL_LEFT || n.state == Node.TRAVEL_RIGHT) {
				Set<PassengerType> types = n.boat.passengerTypes();
				int tripTime = 0;
				
				for (PassengerType t : types) {
					if (t instanceof TorchBearer) {
						
						TorchBearer bearer = (TorchBearer) t;
						if (bearer.getTimeNeeded() > tripTime) {
							tripTime = bearer.getTimeNeeded();
						}
					}
				}
				
				totalTime += tripTime;
			}
		}
		
		return (totalTime <= 15);
	}
	
	/**
	 * Passenger Type
	 */
	private static class TorchBearer extends PassengerType {
		private int crossingTime;
		private String name;
		
		public TorchBearer(int time) {
			crossingTime = time;
			name = String.format("T%d", crossingTime);
		}
		
		public int getTimeNeeded() {
			return crossingTime;
		}
		
		public String getName() {
			return name;
		}
	}
}
