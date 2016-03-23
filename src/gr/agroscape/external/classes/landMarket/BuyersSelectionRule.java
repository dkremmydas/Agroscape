package gr.agroscape.external.classes.landMarket;

import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;

import java.util.List;

/**
 * An abstract class of the selection rule for potential byuers
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.1
 */
public interface BuyersSelectionRule {

	
	public List<HumanAgent> getPotentialBuyers(Plot plot);
	
}
