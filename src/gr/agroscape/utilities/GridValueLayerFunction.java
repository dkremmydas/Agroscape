package gr.agroscape.utilities;

import java.util.ArrayList;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public interface GridValueLayerFunction {

	public void apply(GridValueLayer vl,ArrayList<GridPoint> appliedPoints);
	
}
