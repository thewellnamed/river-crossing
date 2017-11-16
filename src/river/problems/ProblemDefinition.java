package river.problems;

import river.Manifest;

/**
 * Description of a River Crossing Problem
 */
public interface ProblemDefinition {
	Manifest getInitialState();
	String getDescription();
	int getBoatSize();
}
