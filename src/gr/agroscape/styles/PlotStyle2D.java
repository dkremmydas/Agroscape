package gr.agroscape.styles;

import gr.agroscape.agents.Plot;
import gr.agroscape.contexts.Space;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class PlotStyle2D extends DefaultStyleOGL2D {
	
	
	@SuppressWarnings("unused")
	private Space mainContext = Space.getInstance();

	@Override
	public String getLabel(Object object) {
		if(object.getClass().isInstance(Plot.class)) {
			Plot p = (Plot)object;
			return this.mainContext.getLandPropertyRegistry().getOwner(p).getID().toString();
		}
		
		return super.getLabel(object);

	}
	
	
	
	

}
