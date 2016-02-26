package gr.agroscape.behaviors.landMarket.interfaces;

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
public abstract class ClearingMechanism {
	
	private PriceFormationRule priceFormationRule;
	private WinningRule matchingRule;
	
	public abstract List<LandTransaction> getTransactions();
	
}
