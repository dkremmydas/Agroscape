package gr.agroscape.landUse;


public class  ALandUse {

	private static int next_id=1;
	
	/**
	 * A number identifying uniquely the LandUse object. It is assigned implicitly.
	 */
	protected int myId;
	
	

	/**
	 * Constructor
	 * @param name
	 */
	public ALandUse() {
		this(ALandUse.next_id++);		
	}
	
	public ALandUse(int id) {
		this.myId = id;		
	}
	
	
	/**
	 * Getter for myId
	 * @return
	 */
	public int getId() {
		return this.myId;
	}
	
}
