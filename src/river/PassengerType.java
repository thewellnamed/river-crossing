package river;

import java.util.Stack;

/**
 * PassengerType Interface
 */
public interface PassengerType {
	public String getName();
	public boolean validate(Stack<Node> state, Manifest m);
}
