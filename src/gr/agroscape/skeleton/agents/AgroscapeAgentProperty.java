package gr.agroscape.skeleton.agents;

import gr.agroscape.skeleton.agents.plot.Plot;

/**
 * <p>The purpose of this class is to assign to {@link AgroscapeAgent}s properties, 
 * that although are specific to the behavior, may be used by other behaviors too.</p>
 * 
 * <p>An example: For ArableCropProduction Behavior, every {@link Plot} gets an
 *  arableCropLandUse property (of type {link ArableCropCultivation}) that expresses
 *  the {link ArableCropCultivation} that is currently cultivated. Although this property
 *  derives from a specific behavior it is very possible that it will be used by other
 *  behaviors too. So we place it in {@link Plot} properties.
 *  </p>
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 * @param <T> The class of the property. It can be anything.
 */
public class AgroscapeAgentProperty<T> implements Cloneable {

	private Class<T> type;
	private T value;
	private String name;
	
	
	
	protected AgroscapeAgentProperty(Class<T> type, String name, T value) {
		super();
		this.type = type;
		this.value = value;
		this.name = name;
	}
	
	/**
	 * Creates an empty Property
	 * @param type
	 * @param name
	 */
	protected AgroscapeAgentProperty(Class<T> type, String name) {
		super();
		this.type = type;
		this.value = null;
		this.name = name;
	}


	public T getValue() {return this.value;}
	
	public Class<T> getType() {return type;}

	public String getName() {return name;}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "name: " + this.name + ", type: " + this.type + ", value: " + this.value;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
		
	
	
}
