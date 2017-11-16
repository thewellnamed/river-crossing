package river.problems.cannibal;

import river.Manifest;
import river.problems.ProblemDefinition;

/**
 * Missionaries and Cannibals
 *
 * In all locations, at all times:
 * Cannibals must not outnumber Missionaries
 */
public class Definition extends ProblemDefinition {
	public Manifest getInitialState() {
		Manifest m = new Manifest();
		
		m.add(Missionary.type, 3);
	    m.add(Cannibal.type, 3);
	    
	    return m;
	}
	
	public String getDescription() {
		return "Missionaries and Cannibals";
	}
}
