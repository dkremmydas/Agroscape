package gr.agroscape.agents;

import gr.agroscape.contexts.MainContext;
import gr.agroscape.landUse.ALandUse;
import gr.agroscape.landUse.ArableCrop;
import gr.agroscape.main.AgroscapeConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * This is a Plot. Many GridPoints can constitute a Plot.<br /><br />
 * The concern of this class is:
 * <ul>
 * <li> to give information about the Plot (Area, Average Soil Characteristics, etc.)</li>
 * </ul>
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */

public class Plot {

	private static int next_id=0;
	private int myId;
    private ArrayList<GridPoint> gridPoints=new ArrayList<GridPoint>();
    private MainContext mainContext;
    
    private ALandUse landuse;

    /**
     * Create a new Plot from an ArrayList of GridPoints. <br />
     * <strong>Does not</strong> check for duplicate GidPoints in the ArrayList.
     * @param points
     * @param id
     */
    public Plot(ArrayList<GridPoint> points,  int id) {
		super();
		this.mainContext=MainContext.getInstance();
		this.myId=id; Plot.next_id=id++;
		this.gridPoints = points;
	}
    
    /**
     * 
     * @param points
     */
    public Plot(ArrayList<GridPoint> points) {
		this(points,Plot.next_id);
	}
    
    /**
     * 
     * @param point
     */
    public Plot(GridPoint point) {
		this(new ArrayList<GridPoint>(Arrays.asList(point)));
	}
    
    /**
     * 
     * @param point
     * @param id
     */
    public Plot(GridPoint point, int id) {
    	this(new ArrayList<GridPoint>(Arrays.asList(point)),id);
	}
    
    /**
     * 
     * @param points
     * @param id
     */
    public Plot(int[][] points, int id) {
    	super();
    	this.mainContext=MainContext.getInstance();
    	this.myId=id; Plot.next_id=id++;
		for(int i=0;i<points.length;i++) {
			int[] gp = new int[2]; gp[0]=points[i][0];gp[1]=points[i][1];
			this.addGridPoint(new GridPoint(gp));
					//(Integer.valueOf(points[i][0]),Integer.valueOf(points[i][1])));
		}		
	}
    
    /**
     * 
     * @param points
     */
    public Plot(int[][] points) {
    	this(points,Plot.next_id);	
	}

    /**
     * Get the Area of the Plot (hectares)
     * @return
     */
	public double getArea() {
        return this.gridPoints.size()*AgroscapeConfiguration.getGridPointArea();
    }
	
	
	/**
	 * The average Value of all contained GridPoints in the {@link GridValueLayer} data <br/>
	 * For example, if someone wants to calculate the Average yield of the Plot for a specific Crop, he just has to pass the
	 * {@link GridValueLayer} that keeps the Yields for that Crop.
	 * 
	 * @return
	 */
	public double getAverage(GridValueLayer data) {
		
		double r = 0d;
		for (Iterator<GridPoint> iterator = this.gridPoints.iterator(); iterator.hasNext();) {
			GridPoint gridPoint = iterator.next();
			r = (r + data.get(gridPoint.getX(),gridPoint.getY()));
		}
		return r/this.gridPoints.size();
	}
	
	/**
	 * Get the expected yield for the plot for a specified {@link Plot}.
	 * @param c
	 * @return
	 */
	public double getSuitability(ArableCrop c) {
		GridValueLayer gvl = (GridValueLayer) (MainContext.getInstance().getCropsContext().getCropSuitability()).get(c);
		return this.getAverage(gvl);
	}
	
	/**
	 * Get the GridPoints of the Plot
	 * @return
	 */
	public ArrayList<GridPoint> getGridPoints() {
		return gridPoints;
	}

	private void addGridPoint(GridPoint p) {
		if(! this.gridPoints.contains(p))
		this.gridPoints.add(p);
	}
	
	@Override
	public String toString() {
		String r = "["+super.toString()+"]";
		r += " ID=" + this.myId
			 + " / Gridpoints: "
			+ Arrays.toString(this.gridPoints.toArray(new GridPoint[this.gridPoints.size()]))
			+ "\n";
		return r;
	}
	
	
	/**
	 * Clones a Plot
	 */	
	@SuppressWarnings("unchecked")
	@Override
	protected Plot clone() throws CloneNotSupportedException {
		ArrayList<GridPoint> gps = (ArrayList<GridPoint>) this.gridPoints.clone();
		Plot p = new Plot(gps);
		return p;
	}
	
	/**
	 * Get the neighboring plots in a radius of r.
	 * //TODO implement this method
	 * @param r the radius (units ?)
	 * @return
	 */
	public Collection<Plot> getNeighboringPlots(int r)
	{
		return null;
		
	}

	public ALandUse getLanduse() {
		return landuse;
	}

	public void setLanduse(ALandUse landuse) {
		this.landuse = landuse;
	}
	
	
	
}
