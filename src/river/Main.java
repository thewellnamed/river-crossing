package river;

public class Main {
	public static void main(String[] args) {
		Manifest m = new Manifest();
		m.add(Missionary.type, 3);
		m.add(Cannibal.type, 4);
		
		System.out.println(String.format("passengers: %d, valid: %s", m.size(), (m.isValid() ? "yes" : "no")));
	}
}
