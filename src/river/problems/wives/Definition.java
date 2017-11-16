package river.problems.wives;

import java.util.Stack;

import river.Manifest;
import river.Node;
import river.problems.ProblemDefinition;

/**
 * Husbands and Wives
 * Equivalent to Missionaries and Cannibals
 *
 * a Wife may not be in the presence of a Husband unless
 * the Husband has the same ID
 */
public class Definition implements ProblemDefinition {
	
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
		Node current = path.peek();
		
		for (int i = 1; i <= COUPLES_COUNT; i++) {
			Wife w = new Wife(i);
			if (!w.validate(current)) {
				return false;
			}
		}
		
		return true;
	}
}
