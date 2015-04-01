package gr.agroscape.contexts;




import gr.agroscape.crops.Crop;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;



public class CropsContext extends DefaultContext<Crop> {

	
	public CropsContext() {
		super("CropsContext");
	}
	
	
	public ArrayList<Crop> getAvailableCrops() {
		ArrayList<Crop> r = new ArrayList<Crop>();
		
		Iterable<Crop> crops = this.getAgentLayer(Crop.class);
		for (Crop crop : crops) {
			r.add(crop);
		}
		
		return r;
	}
	
	
	
}
