package river;

public class Missionary implements PassengerType {

	private Missionary() {}
	public static Missionary type = new Missionary();
	
	public String getName() {
		return "Missionary";
	}
	
	public boolean validate(Manifest state) {
		if (state.size(Cannibal.type) > state.size(Missionary.type)) {
			return false;
		}
		
		return true;
	}
}
