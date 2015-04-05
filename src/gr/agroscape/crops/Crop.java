package gr.agroscape.crops;

import java.util.HashMap;

public class  Crop {

	private static int next_id=1;
	
	/**
	 * A number identifying uniquely the Crop. It is assigned implicitly.
	 */
	private int myId;
	
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
		this(name,Crop.next_id++);

		
	}
	
	public Crop(String name, int id) {
		super();
		this.myId = id;
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
	public static Crop getCropByName(String n) {
		return Crop.availableCrops.get(n);
	}
	

	@Override
	public String toString() {
		return "Crop::" + this.getName() + " [id=" + this.myId + "]";
	}
	
	
	/**
	 * Getter for myId
	 * @return
	 */
	public int getId() {
		return this.myId;
	}
	
}
