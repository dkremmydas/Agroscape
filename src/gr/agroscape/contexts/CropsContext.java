package gr.agroscape.contexts;




import gr.agroscape.agriculturalActivity.ArableCropCultivation;

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
public class CropsContext extends DefaultContext<ArableCropCultivation> {

	/**
	 * For each available Crop, the true Crop biophysical Suitability
	 */
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitability=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	
	public CropsContext() {
		super("CropsContext");
	}
	
	/**
	 * Get all available Crops
	 * @return
	 */
	public ArrayList<ArableCropCultivation> getAvailableCrops() {
		ArrayList<ArableCropCultivation> r = new ArrayList<ArableCropCultivation>();
		
		Iterable<ArableCropCultivation> crops = this.getAgentLayer(ArableCropCultivation.class);
		for (ArableCropCultivation crop : crops) {
			r.add(crop);
		}
		
		return r;
	}
	
	/**
	 * Get a Crop object by its name (Case insensitive). <br />
	 * @param n String Name of Crop
	 * @return if a crop with that name exists returns a {@link ArableCropCultivation}. Otherwise returns null.
	 */
	public ArableCropCultivation getCropByName(String n) {
		ArrayList<ArableCropCultivation> crops = this.getAvailableCrops();
		for (ArableCropCultivation crop : crops) {
			if(crop.getName().equalsIgnoreCase(n)) return crop;
		}
		return null;
	}
	
	/**
	 * Getter for {@link #cropSuitability}
	 * @return
	 */
	public HashMap<ArableCropCultivation, GridValueLayer> getCropSuitability() {
		return cropSuitability;
	}
	
	
	
}
