package gr.agroscape.external.behaviors.stupido;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.external.behaviors.stupido.properties.StupidoPlotIntegerProperty;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.plot.Plot;

public class StupidoBehavior extends Behavior {
	
	

	public StupidoBehavior(AgroscapeAgent owner, StupidoBehaviorContext bhvContext, StupidoBehaviorFactory bhvFactory) {
		super("Stupido", bhvFactory, owner, bhvContext);
	}


	public void printHappiness() {
		
		//get the random choice and a context
		Integer newNumber = (int) Math.round(Math.random()*100);
		StupidoBehaviorContext sbhvc= (StupidoBehaviorContext)this.getBehaviorContext();
		
		//update plot property
		Plot rPlot = this.getOwner().getMainContext().getPlotsContext().getRandomObject();
		StupidoPlotIntegerProperty prop = (StupidoPlotIntegerProperty) rPlot.getBehaviorProperty(StupidoPlotIntegerProperty.class);
		prop.setValue(new Integer(newNumber));
		
		//update gridvaluelayer
		StupidoBehaviorContext con = (StupidoBehaviorContext) this.getBehaviorContext();
		rPlot.setGridValueLayer(con.getGvl(), newNumber.doubleValue());
		
		System.out.println("I am a happy behavior of agent: " + this.getOwner() 
				+ ". The common property I see is: " + sbhvc.getCommonProperty()
				+ " and I set it to be " + newNumber + "\n"
				+ "I also picked a random Plot: " + rPlot.getId() + "and assign to it"
				+ " the property. So the plot now is: " + rPlot.toString()
			);
		
		sbhvc.setCommonProperty(newNumber);

		
	}

}
