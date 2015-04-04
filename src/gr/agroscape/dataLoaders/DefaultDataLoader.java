package gr.agroscape.dataLoaders;

import gr.agroscape.agents.Agent;
import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Farmer_MP;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.math3.random.HaltonSequenceGenerator;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.StrictBorders;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultDataLoader implements ISimulationDataLoader {
	
	private ArrayList<Plot> avplots = new ArrayList<Plot>();
	private ArrayList<Farmer> avfarmers = new ArrayList<Farmer>();
	private ArrayList<Crop> avcrops = new ArrayList<Crop>();
	
	
	public DefaultDataLoader() {
		super();	
	}

	@Override
	public void loadCropsContext(CropsContext context) {
		this.avcrops.add(new Crop("maize"));
		this.avcrops.add(new Crop("durum wheat"));	
		context.addAll(this.avcrops);
	}


	@Override
	public void loadPlotsContext(PlotsContext context) {
		this.avplots.add(new Plot(new GridPoint(new int[] {1,1}),1));
		this.avplots.add(new Plot(new int[][] {{1,2},{1,3}},2));
		this.avplots.add(new Plot(new int[][] {{1,4},{1,5},{1,6},{1,7}},3));
		this.avplots.add(new Plot(new int[][] {{0,8},{1,8},{1,9},{1,10}},4));
		this.avplots.add(new Plot(new int[][] {{2,1},{2,2},{2,3},{3,1},{3,2},{3,3}},5));
		this.avplots.add(new Plot(new int[][] {{4,1},{4,2},{4,3},{5,1},{5,2},{5,3},{6,1},{6,2},{6,3},{7,1},{7,2},{7,3}},6));
		this.avplots.add(new Plot(new int[][] {{4,4},{4,4},{4,5},{5,4},{5,5},{5,6},{6,4},{6,5},{6,6},{7,4},{7,5},{7,6}},7));
		context.addAll(this.avplots);
	}


	@Override
	public void loadFarmersContext(FarmersContext context) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadPlotsContext should be called before");

		this.avfarmers.add(new Farmer_MP(100000l,this.avcrops,1));
		this.avfarmers.add(new Farmer_MP(100000l,this.avcrops,2));
		this.avfarmers.add(new Farmer_MP(100000l,this.avcrops,3));
		this.avfarmers.add(new Farmer_MP(100000l,this.avcrops,4));
		
		context.addAll(this.avfarmers);
	}


	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		//load owners
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avplots.isEmpty())throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avfarmers.isEmpty())throw new NullPointerException("loadFarmersContext should be called before");
		
		HashMap<Plot,Agent> r = new HashMap<Plot, Agent>();
		r.put(this.avplots.get(0), this.avfarmers.get(0));
		r.put(this.avplots.get(1), this.avfarmers.get(0));
		r.put(this.avplots.get(2), this.avfarmers.get(1));
		r.put(this.avplots.get(3), this.avfarmers.get(1));
		r.put(this.avplots.get(4), this.avfarmers.get(2));
		r.put(this.avplots.get(5), this.avfarmers.get(3));
		r.put(this.avplots.get(6), this.avfarmers.get(3));
		
		lpr.setOwnerRegistry(r);
		
		//load tenants
		HashMap<Plot,Farmer> r2 = new HashMap<Plot, Farmer>();
		lpr.setTenantRegistry(r2);
		
	}



	@Override
	public void initPaymentAuthority(PaymentAuthority pa) {
		HashMap<Crop, Long> coupledPayments=new HashMap<Crop, Long>();
		coupledPayments.put(Crop.getCropByName("maize"), 0l);
		coupledPayments.put(Crop.getCropByName("durum wheat"), 0l);
		
		pa.setCoupledPayments(coupledPayments);		
	}




	


	@Override
	/**
	 * This implementation assigns random suitability for each {Crop->(x,y)}
	 */
	public void loadCropSuitabilityMap(HashMap<Crop, GridValueLayer> csmap,MainContext cm) {

		HaltonSequenceGenerator rnd=new HaltonSequenceGenerator(cm.getGridHeight());
		
		for (Crop c : this.avcrops) {
			csmap.put(c, new GridValueLayer("Yield"+c.getName(),true,new StrictBorders(), cm.getGridWidth(), cm.getGridHeight()));
			for (int i = 0; i < cm.getGridWidth(); i++) {
				double[] d = rnd.nextVector();
				for (int j = 0; j < d.length; j++) {
					double vs = 200*d[j];
					if(vs<100) vs=100;
					csmap.get(c).set(vs, i,j);
				}					
			}
		} //end loop crops
		

	}

	
	
	

}
