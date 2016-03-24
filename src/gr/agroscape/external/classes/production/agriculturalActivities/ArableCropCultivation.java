package gr.agroscape.external.classes.production.agriculturalActivities;

import gr.agroscape.external.classes.production.products.Product;

import java.util.ArrayList;

public class ArableCropCultivation extends AgriculturalActivity {

	
	/**
	 * The name of the Crop
	 */
	private String name;
	
	private String shortName;
	

	/**
	 * Constructor
	 * @param name
	 */
	public ArableCropCultivation(String name,ArrayList<Product> o) {
		super(o);
		this.name = name;
		this.shortName = name;
	}
	
	public ArableCropCultivation(String name,Product o) {
		super(o);
		this.name = name;
		this.shortName = name;
	}
	
	public ArableCropCultivation(String name,String shortN,ArrayList<Product> o) {
		this(name,o);
		this.shortName = shortN;
	}
	
	public ArableCropCultivation(String name,String shortN,Product o) {
		this(name,o);
		this.shortName = shortN;
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public ArableCropCultivation(String name, int id,ArrayList<Product> o) {
		super(id,o);
		this.name = name;
	}


	/**
	 * Getter of name
	 * @return
	 */
	public String getName() {
		return name;
	}


	public String getShortName() {
		return shortName;
	}

	@Override
	public String toString() {
		return "Crop::" + this.getName() + " [id=" + this.myId + "]";
	}
	
	
}
