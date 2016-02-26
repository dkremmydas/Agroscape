package gr.agroscape.landMarket;

import gr.agroscape.landMarket.exceptions.BuyBidLowerSellBidException;
import gr.agroscape.landMarket.exceptions.PlotMismatchException;


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
