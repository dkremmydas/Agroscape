package gr.agroscape.behaviors.landMarket;

import gr.agroscape.behaviors.landMarket.exceptions.BuyBidLowerSellBidException;
import gr.agroscape.behaviors.landMarket.exceptions.PlotMismatchException;
import gr.agroscape.behaviors.landMarket.interfaces.Bid;
import gr.agroscape.behaviors.landMarket.interfaces.BuyersSelectionRule;
import gr.agroscape.behaviors.landMarket.interfaces.ClearingMechanism;
import gr.agroscape.behaviors.landMarket.interfaces.LandMarket;
import gr.agroscape.behaviors.landMarket.interfaces.LandTransaction;
import gr.agroscape.behaviors.landMarket.interfaces.PlotsSelectionRule;
import gr.agroscape.behaviors.landMarket.interfaces.PriceFormationRule;
import gr.agroscape.behaviors.landMarket.interfaces.WinningRule;
import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.random.RandomHelper;

public class DefaultLandMarket extends LandMarket {
	
	private BuyersSelectionRule dbr = new DefaultBuyerSelectionRule();
	private PlotsSelectionRule psr = new DefaultPlotSelectionRule();
	private DefaultClearingMechanism cm = new DefaultClearingMechanism();
	
	private Boolean oneTransaction = false;


	public DefaultLandMarket() {
		super();
		this.setLandMarketRules(this.dbr,this.psr,this.cm);
	}
	


	@Override
	public void clearMarket() {
		
		cm.getTransactions();	
		this.oneTransaction=true;
	}
	

	@Override
	public Boolean isCleared() {
		return this.oneTransaction;
	}
	
	
	
	/**
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	class DefaultBuyerSelectionRule implements BuyersSelectionRule {

		/**
		 * Selects one random farmer to be the buyer
		 */
		@Override
		public List<HumanAgent> getPotentialBuyers(Plot plot) {
			List<HumanAgent> l = new ArrayList<>();
			l.add(SimulationContext.getInstance().getFarmersContext().getRandomObject());
			return l;
		}
		
	}
	
	class DefaultPlotSelectionRule implements PlotsSelectionRule {

		/**
		 * Selects a random Plot to be sold
		 */
		@Override
		public List<Plot> getSelledPlots() {
			List<Plot> p = new ArrayList<>();
			p.add(SimulationContext.getInstance().getPlotsContext().getRandomObject());
			return(p);
		}
		
	}
	
	
	class DefaultClearingMechanism extends ClearingMechanism {
		
		private List<HumanAgent> buyers;
		private List<Plot> selledPlots;
		private WinningRule winningRule;
		

		@Override
		public List<LandTransaction> getTransactions() {
			// TODO Auto-generated method stub
			return null;
		}
		
		/**
		 *  Return a random winner
		 */
		class DefaultWinningRule implements WinningRule {
			@Override
			public Bid getWinner(List<Bid> bids) {
				// TODO Auto-generated method stub
				return bids.get(RandomHelper.nextIntFromTo(1, bids.size()));
			}			
		}
		
		class DefaultPriceFormationRule implements PriceFormationRule {

			/**
			 * Returns a random value between the Seller Bid and the Buyer Bid
			 */
			@Override
			public Long getPrice(Bid buyerBid, Bid sellerBid) throws PlotMismatchException, BuyBidLowerSellBidException {
				if(buyerBid.getThePlot().equals(sellerBid.getThePlot())) {
					if(buyerBid.getTheBid().compareTo(sellerBid.getTheBid())<0) {throw new BuyBidLowerSellBidException();}
					else {		
						double fr = (int)(RandomHelper.nextIntFromTo(1,100)/100);
						return new Long((long) (sellerBid.getTheBid()+ Math.ceil((buyerBid.getTheBid()-sellerBid.getTheBid())*fr)));
					}
				}
				else {
					throw new PlotMismatchException();
				}
				
			}
			
		}
		
	}


	

	
	
}
