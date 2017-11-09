package river;

public class Main {
	public static void main(String[] args) {
		Manifest m = new Manifest();
		m.add(Missionary.type, 2);
		m.add(Cannibal.type, 2);
		
		Solver s = new Solver(m, 2);
		s.solve();
	}
	
	public static class TestType implements PassengerType {
		public String getName() { return "foo"; }
		public boolean validate(Manifest state) { return true; }
	}
}
