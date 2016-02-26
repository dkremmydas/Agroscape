package gr.agroscape.landMarket;


public class LandTransaction {
	
	private Bid sellBid;
	private Bid buyBid;
	private Long thePrice;
	
	public LandTransaction(Bid sellBid, Bid buyBid, Long thePrice) {
		super();
		this.sellBid = sellBid;
		this.buyBid = buyBid;
		this.thePrice = thePrice;
	}

	
	
	public Bid getSellBid() {
		return sellBid;
	}



	public Bid getBuyBid() {
		return buyBid;
	}



	public Long getThePrice() {
		return thePrice;
	} 
	
	
	

}
