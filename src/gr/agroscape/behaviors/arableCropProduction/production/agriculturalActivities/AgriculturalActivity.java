package gr.agroscape.behaviors.arableCropProduction.production.agriculturalActivities;

import gr.agroscape.behaviors.arableCropProduction.production.products.Product;

import java.util.ArrayList;



public abstract class  AgriculturalActivity {

	private static int next_id=1;
	
	/**
	 * A number identifying uniquely the LandUse object. It is assigned implicitly.
	 */
	protected int myId;
	
	/**
	 * An  Agricultural Activity results in one or more {@link Product} outputs
	 */
	protected ArrayList<Product> possibleOutput=new ArrayList<>();
	

	/**
	 * Constructor
	 * @param name
	 */
	protected AgriculturalActivity() {
		this.myId = AgriculturalActivity.next_id++;	
	}
	
	protected AgriculturalActivity(Product p) {
		this();
		this.possibleOutput.add(p);
		
	}
	
	protected AgriculturalActivity(ArrayList<Product> o) {
		this(AgriculturalActivity.next_id++,o);		
	}
	
	protected AgriculturalActivity(int id, ArrayList<Product> o) {
		this.myId = id;		
		this.possibleOutput.addAll(o);
	}
	

	/**
	 * Getter for myId
	 * @return
	 */
	public int getId() {
		return this.myId;
	}

	public ArrayList<Product> getPossibleOutput() {
		return possibleOutput;
	}
	
	
	
}

