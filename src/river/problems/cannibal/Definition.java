package river.problems.cannibal;

import river.Manifest;

/**
 * Missionaries and Cannibals
 *
 * In all locations, at all times:
 * Cannibals must not outnumber Missionaries
 */
public class Definition {
	public static Manifest getInitialState() {
		Manifest m = new Manifest();
		
		m.add(Missionary.type, 3);
	    m.add(Cannibal.type, 3);
	    
	    return m;
	}
}
