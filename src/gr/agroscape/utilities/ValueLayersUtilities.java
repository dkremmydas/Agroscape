package gr.agroscape.utilities;

import repast.simphony.space.Dimensions;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;

/**
 * Utilities (static) related to ValueLayers 
 * @author Dimitris Kremmydas
 *
 */
public final class ValueLayersUtilities {
	
	
	/**
	 * Prints a 2D ValueLayer as a String Matrix
	 * @param vl
	 * @return
	 */
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
	
	
	/**
	 * Returns a copy of ValueLayer
	 * @param in
	 * @return
	 */
	public static void copyGridValueLayers(GridValueLayer from, GridValueLayer to) {
		//check dimensions
		
		//GridValueLayer out = new GridValueLayer(nameOut, true, (int)in.getDimensions().getWidth(),(int)in.getDimensions().getHeight());
		
		Dimensions dim = from.getDimensions();
		
		for(int i=0;i<(int)dim.getWidth();i++) {
			for (int j = 0; j < (int)dim.getHeight(); j++) {
				to.set(from.get(i,j), i,j);
			}
		}

	}

}
