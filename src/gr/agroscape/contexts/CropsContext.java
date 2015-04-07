package gr.agroscape.contexts;




import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;

import repast.simphony.context.DefaultContext;
import repast.simphony.valueLayer.GridValueLayer;


/**
 * It contains information regarding Crops
 * 
 * @author Dimitris Kremmydas
 *
 */
public class CropsContext extends DefaultContext<Crop> {

	/**
	 * For each available Crop, the true Crop biophysical Suitability
	 */
	private HashMap<Crop, GridValueLayer> cropSuitability=new HashMap<Crop, GridValueLayer>();
	
	
	public CropsContext() {
		super("CropsContext");
	}
	
	/**
	 * Get all available Crops
	 * @return
	 */
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
	
	/**
	 * Getter for {@link #cropSuitability}
	 * @return
	 */
	public HashMap<Crop, GridValueLayer> getCropSuitability() {
		return cropSuitability;
	}
	
	
	
}
