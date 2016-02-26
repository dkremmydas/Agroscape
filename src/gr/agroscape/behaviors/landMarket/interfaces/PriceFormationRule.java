package gr.agroscape.behaviors.landMarket.interfaces;

import gr.agroscape.behaviors.landMarket.exceptions.BuyBidLowerSellBidException;
import gr.agroscape.behaviors.landMarket.exceptions.PlotMismatchException;


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
public interface PriceFormationRule {

	public Long getPrice(Bid buyerBid, Bid sellerBid) throws PlotMismatchException,BuyBidLowerSellBidException;
	
}
