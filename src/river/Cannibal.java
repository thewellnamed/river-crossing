package river;

public class Cannibal implements PassengerType {

	private Cannibal() {}
	
	public static Cannibal type = new Cannibal();
	
	public String getName() {
		return "Cannibal";
	}
	
	public boolean validate(Manifest state) {
		return true;
	}
}
