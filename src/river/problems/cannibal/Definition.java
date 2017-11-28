package river.problems.cannibal;

import java.util.Stack;

import river.Manifest;
import river.Node;
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
		
		m.add(Missionary.type, 3);
	    m.add(Cannibal.type, 3);
	    
	    return m;
	}
	
	public int getBoatSize() { 
		return 2;
	}
	
	public String getDescription() {
		return "Missionaries and Cannibals";
	}
	
	public boolean validate(Stack<Node> path) {
		Node state = path.peek();
		
		Manifest[] manifests = { state.left, state.right };
		
		for (Manifest m : manifests) {
			int missionaries = m.size(Missionary.type),
				cannibals = m.size(Cannibal.type);
			
			if (missionaries > 0 && cannibals > missionaries) {
				return false;
			}
		}
		
		return true;
	}
}
