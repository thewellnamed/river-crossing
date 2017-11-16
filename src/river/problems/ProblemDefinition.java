package river.problems;

import river.Manifest;

/**
 * Description of a River Crossing Problem
 */
public abstract class ProblemDefinition {
	public abstract Manifest getInitialState();
	public abstract String getDescription();
}
