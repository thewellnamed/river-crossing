package river;

import river.problems.ProblemDefinition;

/**
 * Demo
 */
public class Main {
	public static void main(String[] args) {
		ProblemDefinition problem;
		int boatSize = 2;
		
		problem = new river.problems.cannibal.Definition();
		//problem = new river.problems.farmer.Definition();
		//problem = new river.problems.wives.Definition();
		
		Solver s = new Solver(problem.getInitialState(), boatSize);		
		Solution solution = s.solve();
		
		System.out.printf("Problem: %s\n", problem.getDescription());
		System.out.println(solution);
	}
}
