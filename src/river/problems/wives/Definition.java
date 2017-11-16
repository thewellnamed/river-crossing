package river.problems.wives;

import river.Manifest;

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
