package gr.agroscape.behaviors;




public class BehaviorProperty<T> {

	private Class<T> type;
	private T value;
	
	
	
	
	public BehaviorProperty(Class<T> type, T value) {
		super();
		this.type = type;
		this.value = value;
	}


	public T getValue() {return this.value;}
	
	 public Class<T> getType() {return type;}
	
}
