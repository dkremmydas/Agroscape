package gr.agroscape.contexts;




import gr.agroscape.landUse.ArableCrop;

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
public class CropsContext extends DefaultContext<ArableCrop> {

	/**
	 * For each available Crop, the true Crop biophysical Suitability
	 */
	private HashMap<ArableCrop, GridValueLayer> cropSuitability=new HashMap<ArableCrop, GridValueLayer>();
	
	
	public CropsContext() {
		super("CropsContext");
	}
	
	/**
	 * Get all available Crops
	 * @return
	 */
	public ArrayList<ArableCrop> getAvailableCrops() {
		ArrayList<ArableCrop> r = new ArrayList<ArableCrop>();
		
		Iterable<ArableCrop> crops = this.getAgentLayer(ArableCrop.class);
		for (ArableCrop crop : crops) {
			r.add(crop);
		}
		
		return r;
	}
	
	/**
	 * Get a Crop object by its name (Case insensitive). <br />
	 * @param n String Name of Crop
	 * @return if a crop with that name exists returns a {@link ArableCrop}. Otherwise returns null.
	 */
	public ArableCrop getCropByName(String n) {
		ArrayList<ArableCrop> crops = this.getAvailableCrops();
		for (ArableCrop crop : crops) {
			if(crop.getName().equalsIgnoreCase(n)) return crop;
		}
		return null;
	}
	
	/**
	 * Getter for {@link #cropSuitability}
	 * @return
	 */
	public HashMap<ArableCrop, GridValueLayer> getCropSuitability() {
		return cropSuitability;
	}
	
	
	
}
