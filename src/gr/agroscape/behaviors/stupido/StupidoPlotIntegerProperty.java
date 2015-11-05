package gr.agroscape.behaviors.stupido;

import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;

public class StupidoPlotIntegerProperty extends AgroscapeAgentProperty<Integer> {

	public StupidoPlotIntegerProperty(Integer value) {
		super(Integer.class, "StupidoInteger", value);
	}

	StupidoPlotIntegerProperty() {
		super(Integer.class, "StupidoInteger", 0);
	}

}
