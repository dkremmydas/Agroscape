package gr.agroscape.landUse;

import java.util.HashMap;

public class ArableCrop extends ALandUse {

		
	/**
	 * Keep an array of name->Crop, so it is handy to get a {@link ArableCrop} object by name
	 */
	private static HashMap<String,ArableCrop> availableCrops=new HashMap<String,ArableCrop>();
	
	/**
	 * The name of the Crop
	 */
	private String name;
	

	/**
	 * Constructor
	 * @param name
	 */
	public ArableCrop(String name) {
		super();
		this.name = name;
		availableCrops.put(name, this);
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public ArableCrop(String name, int id) {
		super(id);
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
	public static ArableCrop getCropByName(String n) {
		return ArableCrop.availableCrops.get(n);
	}
	

	@Override
	public String toString() {
		return "Crop::" + this.getName() + " [id=" + this.myId + "]";
	}
	
	
}
