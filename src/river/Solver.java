package river;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import river.passengers.PassengerType;

/**
 * River Crossing Solver
 *
 * Given an initial state, and a boat size restriction
 * Find solutions to arbitrary river crossing problems
 * Where the rules are defined in the PassengerType implementations
 */
public class Solver {
	private Manifest initialState;
	private int boatSize;
	
	private ArrayList<Node> solution;
	private Stack<Node> stack;
	private HashMap<Node, ArrayList<Manifest>> stateMap;
	
	/**
	 * Initialize
	 */
	public Solver(Manifest initial, int bSize) {
		initialState = initial;
		boatSize = bSize;
		stack = new Stack<Node>();
		stateMap = new HashMap<Node, ArrayList<Manifest>>();
		solution = new ArrayList<Node>();
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
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> solve() {
		Manifest left = initialState.clone();
		Manifest right = new Manifest();
		Manifest emptyBoat = new Manifest();
		solution.clear();
		
		// initialize stack to initial state with possible next moves
		Node root = new Node(Node.BOAT_LEFT, left, right, emptyBoat);
		stack.push(root);
		
		while (!stack.empty()) {
			Node current = pruneStack();
			ArrayList<Manifest> children = getTripPermutations(current);
			
			//System.out.println(String.format("%s", current.prettyString()));
			
			// found solution
			if (current.left.size() == 0 && current.boat.size() == 0 && 
				current.state == Node.BOAT_RIGHT && current.right.size() == initialState.size() &&
				current.isValid()) {
				
				if (solution.isEmpty() || stack.size() < solution.size()) {
					solution = cloneStack();
				}

				stack.pop(); // pop twice back to BOAT_LEFT
				stack.pop(); // and continue
			}
			
			// invalid, no children, or longer than 
			// current solution
			else if ((!solution.isEmpty() && stack.size() >= solution.size()) ||
					 !current.isValid() || 
					 children.size() == 0) {
				//System.out.println("   rejected");
				backtrack(current);
			}
			
			// push next state to test
			else {
				Node n;
				Manifest nextBoat = children.remove(0), newLeft, newRight;
				
				switch (current.state) {
					case Node.BOAT_LEFT:
						newLeft = current.left.clone();
						newLeft.remove(nextBoat);

						n = new Node(Node.TRAVEL_RIGHT, newLeft, current.right, nextBoat);
						stack.push(n);
						break;	
						
					case Node.TRAVEL_RIGHT:
						newRight = current.right.clone();
						newRight.add(current.boat);
						n = new Node(Node.BOAT_RIGHT, current.left, newRight, emptyBoat);
						stack.push(n);
						break;
						
					case Node.BOAT_RIGHT:
						newRight = current.right.clone();
						newRight.remove(nextBoat);
						
						n = new Node(Node.TRAVEL_LEFT, current.left, newRight, nextBoat);
						stack.push(n);
						break;
						
					case Node.TRAVEL_LEFT:
						newLeft = current.left.clone();
						newLeft.add(current.boat);
						
						n = new Node(Node.BOAT_LEFT, newLeft, current.right, emptyBoat);
						stack.push(n);
						break;
				}
			}
		}
		
		return solution;
	}
	
	/**
	 * Backtrack
	 * @param current
	 */
	private void backtrack(Node current) {
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
	public ArrayList<Manifest> getTripPermutations(Node n) {
		if (stateMap.containsKey(n)) {
			return stateMap.get(n);
		}
		
		Manifest m;
		switch (n.state) {
			case Node.BOAT_LEFT:
				m = n.left;
				break;
			
			case Node.BOAT_RIGHT:
				m = n.right;
				break;
				
			case Node.TRAVEL_LEFT:
			case Node.TRAVEL_RIGHT:
				m = n.boat;
				break;
				
			default:
				throw new IllegalStateException("wat");
		}
		
		ArrayList<Manifest> permutations = new ArrayList<Manifest>();
		ArrayList<PassengerType> types = new ArrayList<PassengerType>();
		types.addAll(m.passengerTypes());
		PassengerType first = types.remove(0);
		Manifest trip = new Manifest();
		
		getPermutationsForType(permutations, types, first, m, trip);	
		stateMap.put(n, permutations);
		return permutations;
	}
	
	/**
	 * Recursive helper
	 */
	private void getPermutationsForType(ArrayList<Manifest> permutations, List<PassengerType> types, PassengerType type, Manifest state, Manifest trip) {		
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
	 * Avoids looping by pruning stack when 
	 * a state is found which already exists in the stack
	 * @return
	 */
	private Node pruneStack() {
		Node current = stack.peek();
		int size = stack.size();
		
		if ((current.state == Node.BOAT_LEFT || current.state == Node.BOAT_RIGHT) &&
			size > 4 && stateMap.containsKey(current)) {
			
			boolean seenBefore = false;
			for (int i = size - 4; i >= 0; i--) {
				if (stack.get(i).equals(current)) {
					seenBefore = true;
					break;
				}
			}
			
			// if the stack contains a loop, backtrack...
			if (seenBefore) {
				backtrack(current);
				current = stack.peek();
			}
		}
		
		return current;
	}
	
	/**
	 * Clone current state (For solution)
	 * @return ArrayList<Node>
	 */
	private ArrayList<Node> cloneStack() {
		ArrayList<Node> ret = new ArrayList<Node>();
		for (Node n : stack) {
			Node next = new Node(n.state, n.left.clone(), n.right.clone(), n.boat.clone());
			ret.add(next);
		}
		
		return ret;
	}
}
