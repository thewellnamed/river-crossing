package river.problems.farmer;

import river.Manifest;

public class Definition {
	public static Manifest getInitialState() {
		Manifest m = new Manifest();
		m.add(Farmer.type, 1);
		m.add(Chicken.type, 1);
		m.add(Fox.type, 1);
		m.add(Grain.type, 1);
	    
	    return m;
	}
}
