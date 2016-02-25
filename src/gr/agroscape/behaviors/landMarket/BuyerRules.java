package gr.agroscape.behaviors.landMarket;

import gr.agroscape.skeleton.agents.plot.Plot;


/**
 * <p>The purposes of this class are:</p>
 * <ul>
 * <li></li>
 * </ul>
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.1
 *
 */
public interface BuyerRules {

	public Bid getTheBuyBid(Plot p);
	
	public Long getWtp(Plot p);
	
}
