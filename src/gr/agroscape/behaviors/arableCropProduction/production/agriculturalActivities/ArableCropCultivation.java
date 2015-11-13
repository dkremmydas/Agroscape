package gr.agroscape.behaviors.arableCropProduction.production.agriculturalActivities;

import gr.agroscape.behaviors.arableCropProduction.production.products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArableCropCultivation extends AgriculturalActivity {

		
	/**
	 * Keep an array of name->Crop, so it is handy to get a {@link ArableCropCultivation} object by name
	 */
	private static HashMap<String,ArableCropCultivation> availableCrops=new HashMap<String,ArableCropCultivation>();
	
	/**
	 * The name of the Crop
	 */
	private String name;
	

	/**
	 * Constructor
	 * @param name
	 */
	public ArableCropCultivation(String name,ArrayList<Product> o) {
		super(o);
		this.name = name;
		availableCrops.put(name, this);	
	}
	
	public ArableCropCultivation(String name,Product o) {
		super(o);
		this.name = name;
		availableCrops.put(name, this);	
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public ArableCropCultivation(String name, int id,ArrayList<Product> o) {
		super(id,o);
		this.name = name;
		availableCrops.put(name, this);
	}


	/**
	 * Getter of name
	 * @return
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Get a Crop object by its name
	 * @param n
	 * @return
	 */
	public static ArableCropCultivation getCropByName(String n) {
		return ArableCropCultivation.availableCrops.get(n);
	}
	
	
	/**
	 * Getter
	 * @return
	 */
	public static ArrayList<ArableCropCultivation> getAvailableCrops() {
		ArrayList<ArableCropCultivation> r = new ArrayList<>();
		for(Map.Entry<String,ArableCropCultivation> map : availableCrops.entrySet()){
		     r.add(map.getValue());
		}
		return r;
	}

	@Override
	public String toString() {
		return "Crop::" + this.getName() + " [id=" + this.myId + "]";
	}
	
	
}
