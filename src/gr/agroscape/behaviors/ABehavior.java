package gr.agroscape.behaviors;


/**
 * Every "Farmer Behavior" object contains a reference to the "Farmer" object that contains it.<br />
 * Bhv is a shortcut for "Behavior". So all classes that end with "Bhv" are actually extending 
 * {@link ABehavior}.
 * 
 *   //TODO int he FarmersContext, have a Map of Farmer->ArrayList<ABehavior>
 *   
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class ABehavior<T> {
	
	/**
	 * A reference to the owner Farmer
	 */
	protected T owner;	
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ABehavior(T owner) {
		this.owner = owner;
	}


	/**
	 * Getter
	 * @return
	 */
	public T getOwner() {
		return owner;
	}

}
