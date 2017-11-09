package river;

import java.util.ArrayList;

import river.passengers.*;

/**
 * Demo
 */
public class Main {
	public static void main(String[] args) {
		Manifest m = new Manifest();
		m.add(Farmer.type, 1);
		m.add(Chicken.type, 1);
		m.add(Fox.type, 1);
		m.add(Grain.type, 1);
		
		Solver s = new Solver(m, 2);
		ArrayList<Node> solution = s.solve();
		
		System.out.println("Solution: ");
		System.out.println("----------------\n");
		for (Node n : solution) {
			System.out.println(n.prettyString());
		}
	}
}
