package river.problems.wives;

import river.Manifest;

/**
 * Husbands and Wives
 * Equivalent to Missionaries and Cannibals
 *
 * a Wife may not be in the presence of a Husband unless
 * the Husband has the same ID
 */
public class Definition {
	public static Manifest getInitialState() {
		Manifest m = new Manifest();
		
		for (int i = 1; i <= 3; i++) {
			m.add(new Husband(i), 1);
			m.add(new Wife(i), 1);
		}
	    
	    return m;
	}
}
