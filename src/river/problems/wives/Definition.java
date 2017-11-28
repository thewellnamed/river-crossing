package river.problems.wives;

import java.util.Stack;

import river.Manifest;
import river.Node;
import river.problems.PassengerType;
import river.problems.ProblemDefinition;

/**
 * Husbands and Wives
 * Equivalent to Missionaries and Cannibals
 *
 * a Wife may not be in the presence of any husband which
 * is not her own (same ID) unless her husband is also present
 */
public class Definition implements ProblemDefinition {
	// if you modify this, modify validate()...
	private final int COUPLES_COUNT = 3;

	public Manifest getInitialState() {
		Manifest m = new Manifest();
		
		for (int i = 1; i <= COUPLES_COUNT; i++) {
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
	
	public boolean validate(Stack<Node> path) {
		Node state = path.peek();
		Manifest[] manifests = { state.left, state.right, state.boat };
		int[][] otherHusbands = {{ 1,2 }, { 0,2 }, { 0,1 }};
		
		for (Manifest m : manifests) {
			int husbands[] = { 0, 0, 0 };
			int wives[] = { 0, 0, 0 };
			
			for (PassengerType p: m.passengerTypes()) {
				if (p instanceof Husband) {
					Husband h = (Husband) p;
					husbands[h.getId() - 1]++;
				}
				
				else if (p instanceof Wife) {
					Wife w = (Wife) p;
					wives[w.getId() - 1]++;
				}
			}
			
			for (int i = 0; i < COUPLES_COUNT; i++) {
				if (wives[i] > 0 && husbands[i] == 0) {
					for (int j = 0; j < 2; j++) {
						if (husbands[otherHusbands[i][j]] > 0) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}
