package river.problems.farmer;

import java.util.Stack;

import river.Manifest;
import river.Node;
import river.problems.ProblemDefinition;

/**
 * Farmer, Chicken, Fox, and Grain
 * 
 * - Only the Farmer can drive the boat
 * - The Chicken may not be left alone with the Fox
 * - The Grain may not be left alone with the Chicken
 */
public class Definition implements ProblemDefinition {
	public Manifest getInitialState() {
		Manifest m = new Manifest();
		m.add(Farmer.type, 1);
		m.add(Chicken.type, 1);
		m.add(Fox.type, 1);
		m.add(Grain.type, 1);
	    
	    return m;
	}
	
	public int getBoatSize() {
		return 2;
	}
	
	public String getDescription() {
		return "Farmer, Chicken, Fox, and Grain";
	}
	
	public boolean validate(Stack<Node> path) {
		Node current = path.peek();
		
		if ((current.state == Node.TRAVEL_LEFT || current.state == Node.TRAVEL_RIGHT) &&
			current.boat.size(Farmer.type) == 0) {
			return false;
		}
		
		Manifest[] manifests = { current.left, current.right, current.boat };
		
		for (Manifest m : manifests) {
			int farmers = m.size(Farmer.type),
			    chickens = m.size(Chicken.type),
				foxes = m.size(Fox.type),
				grain = m.size(Grain.type);
			
			if (chickens > 0 && foxes > 0 && farmers == 0) {
				return false;
			}
			
			if (grain > 0 && chickens > 0 && farmers == 0) {
				return false;
			}
		}
		
		return true;
	}
}
