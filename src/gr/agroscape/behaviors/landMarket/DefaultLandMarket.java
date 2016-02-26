package gr.agroscape.behaviors.landMarket;

import gr.agroscape.landMarket.Bid;
import gr.agroscape.landMarket.BuyBidFormationRule;
import gr.agroscape.landMarket.BuyersSelectionRule;
import gr.agroscape.landMarket.LandMarket;
import gr.agroscape.landMarket.LandMarketUtilities;
import gr.agroscape.landMarket.LandTransaction;
import gr.agroscape.landMarket.PerformTransaction;
import gr.agroscape.landMarket.PlotsSelectionRule;
import gr.agroscape.landMarket.PriceFormationRule;
import gr.agroscape.landMarket.SellBidFormationRule;
import gr.agroscape.landMarket.WinningRule;
import gr.agroscape.landMarket.exceptions.BuyBidLowerSellBidException;
import gr.agroscape.landMarket.exceptions.PlotMismatchException;
import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.random.RandomHelper;

public class DefaultLandMarket extends LandMarket {
	
	private BuyersSelectionRule dbr = new DefaultBuyerSelectionRule();
	private PlotsSelectionRule psr = new DefaultPlotSelectionRule();
	private DefaultPerformTransaction cm = new DefaultPerformTransaction();
	
	
	private Boolean oneTransaction = false;


	public DefaultLandMarket() {
		super();
		this.setLandMarketRules(this.dbr,this.psr,this.cm);
		this.fillRegistry();
	}
	
	/**
	 * <p>In the default implementation, get one plot and put only one bid</p>
	 * <p>In other implementations, that would not suffice,but instead for every plot, the bids of all 
	 * potential buyers should be filled to the bidRegistry</p>
	 */
	private void fillRegistry() {
		Plot selectedPlot = this.psr.getSelledPlots().get(0);
		SellBidFormationRule sbfr = LandMarketUtilities.getSellBidFormationRuleFromHumanAgent(
					SimulationContext.getInstance().getLandPropertyRegistry().getOwner(selectedPlot)
				);
		
		HumanAgent selectedBuyer = this.dbr.getPotentialBuyers(selectedPlot).get(0);
		BuyBidFormationRule bbfr = LandMarketUtilities.getBuyBidFormationRuleFromHumanAgent(selectedBuyer);
		
		bidRegistry.put(sbfr.getTheSellBid(selectedPlot), bbfr.getTheBuyBid(selectedPlot));
	}


	@Override
	public void clearMarket() {
		Bid sellBid = bidRegistry.keySet().iterator().next();
		try {
			cm.getTransaction(sellBid, bidRegistry.get(sellBid));
		} catch (BuyBidLowerSellBidException | PlotMismatchException e) { //the transaction could not happen 
			
			e.printStackTrace();
		}
		//cm.getTransaction(bidRegistry., null);	
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
	
	
	class DefaultPerformTransaction extends PerformTransaction {
		
		private WinningRule winningRule = new DefaultWinningRule();
		private PriceFormationRule priceFormationRule = new DefaultPriceFormationRule();

		@Override
		public LandTransaction getTransaction(Bid sellerBid, List<Bid> bids) throws PlotMismatchException, BuyBidLowerSellBidException {
			Bid winnerBid = this.winningRule.getWinner(bids);
			Long price = this.priceFormationRule.getPrice(winnerBid, sellerBid);
			return new LandTransaction(sellerBid,winnerBid, price);
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
