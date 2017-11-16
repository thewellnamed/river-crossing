package river.problems.cowboys;

import river.Manifest;
import river.problems.ProblemDefinition;

/**
 * Missionaries and Cannibals
 *
 * In all locations, at all times:
 * Cannibals must not outnumber Missionaries
 */
public class Definition implements ProblemDefinition {
	public Manifest getInitialState() {
		Manifest m = new Manifest();
		
		m.add(Indian.type, 3);
	    m.add(Cowboy.type, 3);
	    m.add(Mexican.type, 3);
	    
	    return m;
	}
	
	public int getBoatSize() { 
		return 2;
	}
	
	public String getDescription() {
		return "Mexicans, Cowboys, and Indian standoff";
	}
}
