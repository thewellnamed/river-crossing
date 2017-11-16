package river.problems;

import java.util.Stack;

import river.Manifest;
import river.Node;

/**
 * Description of a River Crossing Problem
 */
public interface ProblemDefinition {
	Manifest getInitialState();
	String getDescription();
	int getBoatSize();
	boolean validate(Stack<Node> path);
}
