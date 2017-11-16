package river.problems.cannibal;

import river.Manifest;

public class Definition {
	public static Manifest getInitialState() {
		Manifest m = new Manifest();
		
		m.add(Missionary.type, 3);
	    m.add(Cannibal.type, 3);
	    
	    return m;
	}
}
