package gr.agroscape.behaviors.farmers.production.products;

import java.util.HashMap;


public class  Product {

	private static int next_id=1;
	
	/**
	 * A number identifying uniquely the LandUse object. It is assigned implicitly.
	 */
	protected int myId;
	
	/**
	 * The name of the Crop
	 */
	private String name;
	
	/**
	 * Keep an array of name->Product, so it is handy to get a {@link Product} object by name
	 */
	private static HashMap<String,Product> availableProducts=new HashMap<String,Product>();
	
	

	/**
	 * Constructor
	 * @param name
	 */
	public Product( String name) {
		this(Product.next_id++,name);		
	}
	
	public Product(int id, String name) {
		this.myId = id;		
		this.name = name;
		Product.availableProducts.put(name, this);
	}
	
	
	/**
	 * Getter for myId
	 * @return
	 */
	public int getId() {
		return this.myId;
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
	public static Product getByName(String n) {
		return Product.availableProducts.get(n);
	}
	
	
	/**
	 * Get all instantiated {@link Product}s
	 * @return
	 */
	public static HashMap<String, Product> getAvailableProducts() {
		return availableProducts;
	}

	@Override
	public String toString() {
		return "\nProduct::" + this.getName() + " [id=" + this.myId + "]";
	}
	
	
	
	
}
