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
	
	/**
	 * Get a Crop object by its name (Case insensitive). <br />
	 * @param n String Name of Crop
	 * @return if a crop with that name exists returns a {@link Crop}. Otherwise returns null.
	 */
	public Crop getCropByName(String n) {
		ArrayList<Crop> crops = this.getAvailableCrops();
		for (Crop crop : crops) {
			if(crop.getName().equalsIgnoreCase(n)) return crop;
		}
		return null;
	}
	
	
	
	
}
