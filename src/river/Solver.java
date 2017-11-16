package river;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import river.problems.PassengerType;
import river.problems.ProblemDefinition;

/**
 * River Crossing Solver
 *
 * Given an initial state, and a boat size restriction
 * Find solutions to arbitrary river crossing problems
 * Where the rules are defined in the PassengerType implementations
 */
public class Solver {
	// reject states if stack gets too deep
	private static final int DEFAULT_MAX_STACK_DEPTH = 100;
	
	// enable optimization to prune states
	// that loop back to a previous state
	private static final boolean LOOP_CHECK = true;
	
	private ProblemDefinition problem;
	private Manifest initialState;
	private int maxDepth;
	private Solution solution;
	private Stack<Node> stack;
	private Node current;
	private HashMap<Node, ArrayList<Manifest>> nodeChildren;
	
	/**
	 * Initialize
	 */
	public Solver(ProblemDefinition problem) {
		this(problem, DEFAULT_MAX_STACK_DEPTH);
	}
	
	public Solver(ProblemDefinition definition, int depth) {
		problem = definition;
		initialState = problem.getInitialState();
		current = null;
		maxDepth = depth;
		stack = new Stack<Node>();
		nodeChildren = new HashMap<Node, ArrayList<Manifest>>();
	}
	
	/**
	 * Find a solution
	 * 
	 * Returns the shortest solution found
	 * Uses a backtracking strategy to explore
	 * the space of possible solutions
	 * 
	 * Returns a list of successive node states
	 * leading from the initial state to a solution state
	 * @return Solution
	 */
	public Solution solve() {
		Manifest left = initialState.clone();
		Manifest right = new Manifest();
		Manifest emptyBoat = new Manifest();
		solution = new Solution(null);
		
		// initialize stack to initial state
		Node root = new Node(Node.BOAT_LEFT, left, right, emptyBoat);
		stack.push(root);
		
		long start = System.currentTimeMillis();
		while (!stack.empty()) {
			++solution.iterations;
			current = stack.peek();
			ArrayList<Manifest> children = getTripPermutations(current);
			
			//System.out.println(String.format("Next: %s", current.prettyString()));
			
			// is current state a solution?
			if (foundSolution()) {
				++solution.count;
					
				if (solution.empty() || stack.size() < solution.size()) {
					solution.setPath(cloneStack());
				}

				stack.pop(); // pop twice back to BOAT_LEFT
				stack.pop(); // and continue
			}
			
			// is current state a dead-end?
			else if (rejectState(children)) {
				//System.out.println("   rejected");
				backtrack();
			}
			
			// push next state onto the stack
			else {
				Manifest nextBoat, newLeft, newRight;
				
				switch (current.state) {
					case Node.BOAT_LEFT:
						nextBoat = children.get(current.nextChild++);
						newLeft = current.left.clone();
						newLeft.remove(nextBoat);
						stack.push(new Node(Node.TRAVEL_RIGHT, newLeft, current.right, nextBoat));
						break;	
												
					case Node.BOAT_RIGHT:
						nextBoat = children.get(current.nextChild++);
						newRight = current.right.clone();
						newRight.remove(nextBoat);
						stack.push(new Node(Node.TRAVEL_LEFT, current.left, newRight, nextBoat));
						break;
						
					case Node.TRAVEL_LEFT:
						newLeft = current.left.clone();
						newLeft.add(current.boat);
						stack.push(new Node(Node.BOAT_LEFT, newLeft, current.right, emptyBoat));
						break;
						
					case Node.TRAVEL_RIGHT:
						newRight = current.right.clone();
						newRight.add(current.boat);
						stack.push(new Node(Node.BOAT_RIGHT, current.left, newRight, emptyBoat));
						break;
				}
			}
		}

		solution.statesVisited = nodeChildren.keySet().size();
		solution.elapsedSeconds = (System.currentTimeMillis() - start) / 1000F;
		
		return solution;
	}
	
	/**
	 * Current state is a solution?
	 */
	private boolean foundSolution() {
		return (current.left.size() == 0 && current.boat.size() == 0 && 
				current.state == Node.BOAT_RIGHT && current.right.size() == initialState.size() &&
				problem.validate(stack));
	}
	
	/**
	 * Current state is a dead end?
	 */
	private boolean rejectState(ArrayList<Manifest> children) {
		return ((!solution.empty() && stack.size() >= solution.size()) ||
				stack.size() > maxDepth || 
			    (children != null && children.size() <= current.nextChild) ||
				!problem.validate(stack) || foundLoop());
	}
	
	/**
	 * Current state duplicates a previous state in the stack?
	 */
	private boolean foundLoop() {
		if (LOOP_CHECK) {
			int size = stack.size();
			
			if ((current.state == Node.BOAT_LEFT || current.state == Node.BOAT_RIGHT) &&
				size > 4 && nodeChildren.containsKey(current)) {
			
				for (int i = size - 4; i >= 0; i--) {
					if (stack.get(i).equals(current)) {
						return true;
					}
				}
			}
		}
			
		return false;
	}
	
	/**
	 * Backtrack
	 * @param current
	 */
	private void backtrack() {
		stack.pop();
		
		// since transition from TRAVEL states to LEFT/RIGHT
		// is automatic, pop back one more state to avoid
		// unnecessary looping
		if (stack.size() > 1 && 
			(current.state == Node.BOAT_LEFT || current.state == Node.BOAT_RIGHT)) {
			stack.pop();
		}
	}
		
	/**
	 * Return passenger permutations of size boatSize from types in manifest
	 * @param m Manifest
	 * @return ArrayList<Manifest>
	 */
	private ArrayList<Manifest> getTripPermutations(Node n) {
		if (nodeChildren.containsKey(n)) {
			return nodeChildren.get(n);
		}
		
		Manifest m = null;
		ArrayList<Manifest> permutations = null;
		
		switch (n.state) {
			case Node.BOAT_LEFT:
				m = n.left;
				break;
			
			case Node.BOAT_RIGHT:
				m = n.right;
				break;
		}
		
		if (m != null) {
			permutations = new ArrayList<Manifest>();
			ArrayList<PassengerType> types = new ArrayList<PassengerType>();
			types.addAll(m.passengerTypes());
			PassengerType first = types.remove(0);
			Manifest trip = new Manifest();
			
			getPermutationsForType(permutations, types, first, m, trip);	
		}
		
		nodeChildren.put(n, permutations);
		return permutations;
	}
	
	/**
	 * Recursive helper
	 */
	private void getPermutationsForType(ArrayList<Manifest> permutations, List<PassengerType> types, PassengerType type, Manifest state, Manifest trip) {		
		int boatSize = problem.getBoatSize();
		if (trip.size() == boatSize) {
			return;
		}
		
		int max = Math.min(boatSize - trip.size(), state.size(type));		
		for (int i = max; i >= 0; i--) {
			Manifest next = trip.clone();
			next.add(type,  i);

			if (next.size() == boatSize || (types.isEmpty() && next.size() > 0)) {
				permutations.add(next);
			} else if (!types.isEmpty()) {
				getPermutationsForType(permutations, types.subList(1, types.size()), types.get(0), state, next);
			}
		}
	}
	
	/**
	 * Clone current state (For solution)
	 * @return ArrayList<Node>
	 */
	private Stack<Node> cloneStack() {
		Stack<Node> ret = new Stack<Node>();
		for (Node n : stack) {
			Node next = new Node(n.state, n.left.clone(), n.right.clone(), n.boat.clone());
			ret.add(next);
		}
		
		return ret;
	}
}
