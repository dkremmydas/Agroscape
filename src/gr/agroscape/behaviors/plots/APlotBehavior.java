package gr.agroscape.behaviors.plots;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.IScheduledBehavior;

/**
 * An abstract class defining basic properties for an implementation of a PlotBehavior 
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class APlotBehavior implements IScheduledBehavior<Plot> {

	/**
	 * The owner of the behavior
	 */
	protected final Plot owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public APlotBehavior(Plot owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
