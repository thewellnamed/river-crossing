package river.problems.wives;

import river.Manifest;
import river.problems.ProblemDefinition;

/**
 * Husbands and Wives
 * Equivalent to Missionaries and Cannibals
 *
 * a Wife may not be in the presence of a Husband unless
 * the Husband has the same ID
 */
public class Definition implements ProblemDefinition {
	public Manifest getInitialState() {
		Manifest m = new Manifest();
		
		for (int i = 1; i <= 3; i++) {
			m.add(new Husband(i), 1);
			m.add(new Wife(i), 1);
		}
	    
	    return m;
	}
	
	public int getBoatSize() {
		return 2;
	}
	
	public String getDescription() {
		return "Husbands and Wives";
	}
}
