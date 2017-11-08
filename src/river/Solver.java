package river;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solver {
	private Manifest initialState;
	private int boatSize;
	
	private ArrayList<Crossing> solution;
	private Stack<Node> stack;
	
	public Solver(Manifest initial, int bSize) {
		initialState = initial;
		boatSize = bSize;
		stack = new Stack<Node>();
	}
	
	public ArrayList<Crossing> solve() {
		Manifest left = initialState.clone();
		Manifest right = new Manifest();
		
		for (Node n : getValidPermutations(left)) {
			stack.push(n);
		}
		
		while (!stack.empty()) {
			Node next = stack.peek();
		}
		
		return solution;
	}
	
	/**
	 * Return passenger permutations of size boatSize from types in manifest
	 * @param m Manifest
	 * @return ArrayList<Manifest>
	 */
	private ArrayList<Node> getValidPermutations(Manifest m) {
		return new ArrayList<Node>();
	}
	
	private class Node {
		public Manifest left;
		public Manifest right;
		public List<Crossing> history;
		
		public Node(Manifest l, Manifest r, List<Crossing> prev) {
			left = l;
			right = r;
			history = prev;
		}
	}
}
