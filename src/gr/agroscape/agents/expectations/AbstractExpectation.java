package gr.agroscape.agents.expectations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class for an Expectation. <br />
 * An Expectation has the form of Key->Value and those two can be of any type. <br />
 * For example a "Crop Prices" expectation is of the form Crop->Long. <br />
 * <br />
 * There are two ways to instantiate an object: Either by giving explicitly the K->V pairs,
 * or by giving only the K values and implicitly assigning the default values to them.<br /> 
 * <br />
 * In order to subclass, an override implementation of {@link #getDefaultValues(ArrayList) getDefaultValues} is necessary.
 *
 * @author jkr
 * @param <V> the Value
 * @param <K> the Key
 *
 */
public abstract class AbstractExpectation<K, V> {
	//TODO: Write more on what an expectation is
	
	private HashMap<K,V> values;
	
	
	/**
	 * The constructor, if the values are given
	 * @param values
	 */
	public AbstractExpectation(HashMap<K, V> values) {
		super();
		this.values = values;
	}
	
	/**
	 * The constructor, forcing the default values to be loaded
	 * @param values
	 */
	public AbstractExpectation(ArrayList<K> keys) {
		super();
		this.values = this.getDefaultValues(keys);
	}
	
	
	/**
	 * Get all expectations
	 * @return
	 */
	public HashMap<K,V> getAll() {
		return this.values;
	}
	
	/**
	 * Get the Expectation for a Key
	 * @param k
	 * @return
	 */
	public V get(K k) {
		return this.values.get(k);
	}
	
	
	@Override
	public String toString() {
		String r = "";
		
		HashMap<K,V> v = this.values;
		for (K key : v.keySet()) {
		    r += key.toString() + ": " + v.get(key).toString();
		}
		
		return r;
	}
	

	/**
	 * Each subclass has to implement the assignment of default values to the given ArrayList of keys
	 * @param keys
	 * @return
	 */
	abstract HashMap<K,V> getDefaultValues(ArrayList<K> keys);
	
}
