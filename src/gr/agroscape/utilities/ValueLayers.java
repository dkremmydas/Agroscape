package gr.agroscape.utilities;

import repast.simphony.space.Dimensions;
import repast.simphony.valueLayer.ValueLayer;

/**
 * Utilities (static) related to ValueLayers 
 * @author jkr
 *
 */
public final class ValueLayers {
	
	
	
	public static String getValueLayerAsPrintedMatrix(ValueLayer vl) {
		
		String r="";
		
		Dimensions dim = vl.getDimensions();
				
		for(int i=0;i<(int)dim.getWidth();i++) {
			for (int j = 0; j < (int)dim.getHeight(); j++) {
				r += String.format("%4.2f ", (float)vl.get(i,j));
			}
			r += "\n";
		}
		
		return r;
	}

}
