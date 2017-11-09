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
		Manifest boat = new Manifest();
		
		// initialize stack to initial state with possible next moves
		Node root = new Node(left, right, boat, getTripPermutations(left), Node.BOAT_EMPTY);
		stack.push(root);
		
		while (!stack.empty()) {
			Node next = stack.peek();
			
			// if next is a solution:
			//    -- left is empty AND
			//    -- boat is empty AND
			//    -- right is valid AND 
			//    -- shorter than current solution or no current solution
			//    then save the solution and pop()?
			//
			// else if cannot proceed
			//    -- next is invalid OR
			//    -- next has no children OR
			//    -- next already as long as existing solution
			//    then pop()
			// else 
            //    -- remove first child from next and push on stack with its own children
			//    
			//    the remove makes sure when we pop back to this state
			//    that we don't try to evaluate the same child again
			//    when pushing, create a new node, passing getTripPermutations()
			//    The Manifest to get permutations on is dependent on the last state
			//    -- if we're sending a boat, get permutations of next.left
			//    -- if a boat is returning, get permutations of next.right + next.boat
			
			System.out.println(String.format("Eval node: %s", next));
			
			if (next.left.size() == 0 && next.boat.size() == 0 && next.right.isValid()) {
				System.out.println("found a solution!");
				stack.pop();
			}
			
			else if (!next.left.isValid() ||
					 !next.boat.isValid() ||
					 !next.right.isValid() ||
					 next.children.size() == 0) {
				System.out.println("--> rejected");
				stack.pop();
			}
			else {
				Manifest step = next.children.remove(0), newBranchState, newLeft, newRight;
				Node n;
				switch (next.state) {
					case Node.BOAT_EMPTY:
						newLeft = next.left.clone();
						newLeft.remove(step);
						
						newBranchState = step.clone();
						newBranchState.add(next.right);
						
						n = new Node(newLeft, next.right, step, getTripPermutations(newBranchState), Node.BOAT_SENDING);
						stack.push(n);
						break;					
				}
			}
		}
		
		return solution;
	}
	
	/**
	 * Return passenger permutations of size boatSize from types in manifest
	 * @param m Manifest
	 * @return ArrayList<Manifest>
	 */
	public ArrayList<Manifest> getTripPermutations(Manifest state) {
		ArrayList<Manifest> permutations = new ArrayList<Manifest>();
		ArrayList<PassengerType> types = new ArrayList<PassengerType>();
		types.addAll(state.passengerTypes());
		PassengerType first = types.remove(0);
		Manifest trip = new Manifest();
		
		getPermutationsForType(permutations, types, first, state, trip);	
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
	
	private class Node {
		public static final int BOAT_EMPTY = 0;
		public static final int BOAT_SENDING = 1;
		public static final int BOAT_RETURNING = 2;
		
		public Manifest left;
		public Manifest right;
		public Manifest boat;
		public List<Manifest> children;
		public int state;
		
		public Node(Manifest l, Manifest r, Manifest b, List<Manifest> branches, int crossingType) {
			left = l;
			right = r;
			boat = b;
			children = branches;
			state = crossingType;
		}
		
		@Override
		public String toString() {
			return String.format("left=%s, boat=%s, right=%s, type=%d, children=%s", left, boat, right, state, children);
		}
	}
}
