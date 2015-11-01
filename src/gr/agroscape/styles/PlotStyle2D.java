package gr.agroscape.styles;

import gr.agroscape.agents.plot.Plot;
import gr.agroscape.contexts.SimulationContext;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class PlotStyle2D extends DefaultStyleOGL2D {
	
	
	@SuppressWarnings("unused")
	private SimulationContext mainContext = SimulationContext.getInstance();

	@Override
	public String getLabel(Object object) {
		if(object.getClass().isInstance(Plot.class)) {
			Plot p = (Plot)object;
			return this.mainContext.getLandPropertyRegistry().getOwner(p).getId().toString();
		}
		
		return super.getLabel(object);

	}
	
	
	
	

}
