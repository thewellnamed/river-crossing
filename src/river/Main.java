package river;

/**
 * Demo
 */
public class Main {
	public static void main(String[] args) {	
		int boatSize = 2;
		Manifest init = river.problems.cannibal.Definition.getInitialState();
		//Manifest init = river.problems.farmer.Definition.getInitialState();
		//Manifest init = river.problems.wives.Definition.getInitialState();
		
		Solver s = new Solver(init, boatSize);		
		Solution solution = s.solve();
		
		System.out.println(solution);
	}
}
