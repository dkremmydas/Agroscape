package gr.agroscape.external.behaviors.stupido.properties;

import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;

public class StupidoPlotIntegerProperty extends AgroscapeAgentProperty<Integer> {

	public StupidoPlotIntegerProperty(Integer value) {
		super(Integer.class, "StupidoInteger", value);
	}

	public StupidoPlotIntegerProperty() {
		super(Integer.class, "StupidoInteger", 0);
	}

}
