package river;

import java.util.Stack;

/**
 * Information from a Solver run
 */
public class Solution {
	public int count;
	public int iterations;
	public int statesVisited;
	public double elapsedSeconds;
	public Stack<Node> solutionPath;
	
	/**
	 * Initialize with solution path
	 * @param path
	 */
	public Solution(Stack<Node> path) {
		solutionPath = path;
	}
	
	/**
	 * Update the best solution path
	 * @param path
	 */
	public void setPath(Stack<Node> path) {
		solutionPath = path;
	}
	
	/*
	 * Have solution?
	 */
	public boolean empty() {
		return solutionPath == null;
	}
	
	/**
	 * Size of solution
	 * @return
	 */
	public int size() {
		return (solutionPath == null) ? 0 : solutionPath.size();
	}
	
	/**
	 * Pretty output
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append(String.format("Elapsed Time: %.2fs\n", elapsedSeconds));
		s.append(String.format("Iteration Count: %d\n", iterations));
		s.append(String.format("States Visited: %d\n", statesVisited));
		s.append(String.format("Solutions found: %d\n\n", count));
		
		if (solutionPath != null) {
			s.append(String.format("Solution (%d crossings):\n\n", getCrossingCount()));
			for (Node n : solutionPath) {
				s.append(n.prettyString());
				s.append("\n");
			}
		}
		return s.toString();
	}
	
	/**
	 * Returns the number of crossings
	 * @return
	 */
	public long getCrossingCount() {
		if (solutionPath == null) {
			return 0L;
		}
		
		return solutionPath.stream().filter(n -> n.state == Node.TRAVEL_LEFT || n.state == Node.TRAVEL_RIGHT).count();
	}
}
