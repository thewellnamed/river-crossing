package river.problems.cowboys;

import java.util.Stack;

import river.Manifest;
import river.Node;
import river.problems.ProblemDefinition;

/**
 * Mexican Standoff
 * 
 * - Cowboys must not outnumber (Mexicans + Indians)
 * - Mexicans must not outnumber Cowboys
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
	
	public boolean validate(Stack<Node> path) {
		Node state = path.peek();
		
		Manifest[] manifests = { state.left, state.right, state.boat };
		
		for (Manifest m : manifests) {
			int cowboys = m.size(Cowboy.type),
				indians = m.size(Indian.type),
				mexicans = m.size(Mexican.type);
			
			if (cowboys > 0 && (indians + mexicans) < cowboys) {
				return false;
			}
			
			if (mexicans > 0 && cowboys < mexicans) {
				return false;
			}
		}
		
		return true;
	}
}
