package river;

import java.util.ArrayList;

/**
 * Demo
 */
public class Main {
	public static void main(String[] args) {
		Manifest m = new Manifest();
		m.add(Missionary.type, 3);
		m.add(Cannibal.type, 3);
		//m.add(new TestType(), 3);
		
		Solver s = new Solver(m, 2);
		ArrayList<Node> solution = s.solve();
		
		System.out.println("Solution: ");
		System.out.println("----------------\n");
		for (Node n : solution) {
			System.out.println(n.prettyString());
		}
	}
	
	public static class TestType extends PassengerType {
		public String getName() {
			return "T";
		}
		
		/**
		 * Can only be on right shore if there's at least
		 * one Missionary
		 */
		public boolean validate(Node n) {
			if (n.right.size(this) > 0 && 
				n.right.size(Missionary.type) == 0) {
					return false;
			}
			
			return true;
		}
	}
}
