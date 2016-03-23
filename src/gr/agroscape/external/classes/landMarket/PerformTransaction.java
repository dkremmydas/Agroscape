package gr.agroscape.external.classes.landMarket;

import gr.agroscape.external.classes.landMarket.exceptions.BuyBidLowerSellBidException;
import gr.agroscape.external.classes.landMarket.exceptions.PlotMismatchException;

import java.util.List;



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
public abstract class PerformTransaction {
	
	private PriceFormationRule priceFormationRule;
	private WinningRule matchingRule;
	
	public abstract LandTransaction getTransaction(Bid sellerBid, List<Bid> bids) throws PlotMismatchException, BuyBidLowerSellBidException;
	
}
