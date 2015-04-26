package gr.agroscape.behaviors;


/**
 * An abstract class defining the core operation that a {@link ABehavedObject}
 * belongs to an Owner object.
 * 
 * @author Dimitris Kremmydas
 * @param <T>
 *
 */
public abstract class ABehavedObject<T> implements IScheduledBehavior<T> {

	/**
	 * The owner of the behavior
	 */
	protected T owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ABehavedObject(T owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
