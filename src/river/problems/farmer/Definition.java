package river.problems.farmer;

import river.Manifest;
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
}
