package gr.agroscape.behaviors.plots;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.ScheduledBehavior;

/**
 * An abstract class defining basic properties for an implementation of a PlotBehavior 
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class APlotAction implements ScheduledBehavior {

	/**
	 * The owner of the behavior
	 */
	protected final Plot owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public APlotAction(Plot owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
