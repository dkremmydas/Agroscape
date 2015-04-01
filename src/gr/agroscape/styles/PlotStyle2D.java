package gr.agroscape.styles;

import gr.agroscape.agents.Plot;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class PlotStyle2D extends DefaultStyleOGL2D {
	
	
	@SuppressWarnings("unused")
	private MainContext mainContext = MainContext.getInstance();

	@Override
	public String getLabel(Object object) {
		if(object.getClass().isInstance(Plot.class)) {
			Plot p = (Plot)object;
			return this.mainContext.getLandPropertyRegistry().getOwner(p).getID().toString();
		}
		
		return super.getLabel(object);

	}
	
	
	
	

}
