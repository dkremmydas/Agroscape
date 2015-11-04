package gr.agroscape.behaviors.farmers.production.agriculturalActivities;

import gr.agroscape.behaviors.farmers.production.products.Product;

import java.util.ArrayList;


public abstract class  AAgriculturalActivity {

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
	protected AAgriculturalActivity() {
		this.myId = AAgriculturalActivity.next_id++;	
	}
	
	protected AAgriculturalActivity(Product p) {
		this();
		this.possibleOutput.add(p);
		
	}
	
	protected AAgriculturalActivity(ArrayList<Product> o) {
		this(AAgriculturalActivity.next_id++,o);		
	}
	
	protected AAgriculturalActivity(int id, ArrayList<Product> o) {
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
