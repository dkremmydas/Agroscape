package gr.agroscape.external.classes.landMarket;

import gr.agroscape.skeleton.agents.plot.Plot;

import java.util.List;

/**
 * An abstract class of the selection rule for plots that under sell
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.1
 */
public interface PlotsSelectionRule {

	
	public List<Plot> getSelledPlots();
	
}
