package river;

import river.problems.ProblemDefinition;

/**
 * Demo
 */
public class Main {
	public static void main(String[] args) {
		ProblemDefinition problem;
		
		problem = new river.problems.cannibal.Definition();
		//problem = new river.problems.farmer.Definition();
	    //problem = new river.problems.wives.Definition();
		//problem = new river.problems.cowboys.Definition();
		
		Solver s = new Solver(problem);		
		boolean exitOnFirstSolution = true;
		
		Solution solution = s.solve(exitOnFirstSolution);
		
		System.out.printf("Problem: %s\n", problem.getDescription());
		System.out.println(solution);
	}
}
