package gr.agroscape.crops;

import java.util.HashMap;

public class  Crop {

	/**
	 * Keep an array of name->Crop, so it is handy to get a {@link Crop} object by name
	 */
	private static HashMap<String,Crop> availableCrops=new HashMap<String,Crop>();
	
	/**
	 * The name of the Crop
	 */
	private String name;
	
	

	/**
	 * Constructor
	 * @param name
	 */
	public Crop(String name) {
		super();
		this.name = name;
		availableCrops.put(name, this);
	}


	public String getName() {
		return name;
	}

	
	public static Crop getCropByName(String n) {
		return Crop.availableCrops.get(n);
	}
	

	@Override
	public String toString() {
		return "Crop::" + this.getName() + " [" + super.toString() + "]";
	}
	
}
