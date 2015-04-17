package gr.agroscape.agents;

/**
 * A Property of a Human Agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class HumanAgentProperty<V> {
	
	
	
	/**
	 * The object stored
	 */
	V value;

	/**
	 * Constructor
	 * @param name
	 * @param type
	 * @param value
	 */
	public HumanAgentProperty(V value) {
		super();
		this.value = value;
	}
	
	
	public V getValue() {
		return this.value;
	}
	
	
	
}
