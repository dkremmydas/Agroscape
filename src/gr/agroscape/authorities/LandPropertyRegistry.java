package gr.agroscape.authorities;

import gr.agroscape.agents.Agent;
import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.exceptions.NoSuchAgentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.linear.BlockRealMatrix;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The Land Property Registry of the simulation </br>
 * The concern of this class is 
 * <ul>
 * <li>to keep information regarding which {@link Agent} owns which {@link Plot}</li>
 * <li>which {@link Farmer} cultivates which {@link Plot}, i.e. owns+rents</li>
 * <li>To give information on a {@link Farmer} for the {@link Plot} he cultivates</li>
 * </ul>
 * <p>The implementation is based on {@link org.apache.commons.math3.linear.BlockRealMatrix BlockRealMatrix} use. 
 * For example for keeping record of the Plot owners, see {@link #ownerRegistry}.</p>
 * 
 * @author Dimitris Kremmydas
 */
public class LandPropertyRegistry {
	
	private final static int defaultNumOfAgents = 10;
	private final static int defaultNumOfPlots = 100;
	
	private ArrayList<Plot> plots;
	private ArrayList<Farmer> tenants;
	private ArrayList<Agent> owners;
	
	/**
	 * The owner's registry.  <br />
	 * Row dimension is for agents. The number of row equals to the {@link gr.agroscape.agents.Agent#myID}. <br />
	 * Column dimension is for Plots and it holds the plot_num. <br />
	 * If the value of (i,j) element equals to 1, then Agent_num=i owns Plot_num=j. <br />
	 */
	private BlockRealMatrix ownerRegistry;
	
	/**
	 * The tenan't registry.  <br />
	 * Row dimension is for tenants (farmers) and it equal to the tenant_num. <br />
	 * Column dimension is for Plots and it holds the plot_num. <br />
	 * If the value of (i,j) element equals to 1, then Tenant_num=i owns Plot_num=j. <br />
	 */
	private BlockRealMatrix tenantRegistry;
	
	
	
	/**
	 * Creates an empty LandPropertyRegistry
	 * @param numOfAgents
	 * @param numOfPlots
	 */
	public LandPropertyRegistry(int numOfAgents, int numOfPlots) {
		super();
		//initialize registry and arrays
		ownerRegistry = new BlockRealMatrix(numOfAgents,numOfPlots);
		tenantRegistry = new BlockRealMatrix(numOfAgents,numOfPlots);
		plots = new ArrayList<Plot>(numOfPlots);
		tenants = new ArrayList<Farmer>(numOfAgents);
		owners = new ArrayList<Agent>(numOfAgents);
	}
	
	/**
	 * Create a LandPropertyRegistry with the default number of agents
	 */
	public LandPropertyRegistry() {
		this(LandPropertyRegistry.defaultNumOfAgents,LandPropertyRegistry.defaultNumOfPlots);
	}
	
	
	
	/**
	 * Sets an entry in the ownerRegistry. <br />
	 * @param p Plot
	 * @param a Agent
	 */
	public void setOwnerEntry(Plot p, Agent a) {
		//check if p in plots
		if(! this.plots.contains(p)) this.plots.add(p);
		
		//check if a in owners
		if(! this.owners.contains(a)) this.owners.add(a);
		
		//find agent_num and plot_num
		int agent_num = this.owners.indexOf(a);
		int plot_num = this.plots.indexOf(p);
		
		//check if we need to increase the registry
		if(this.plots.size()>=this.ownerRegistry.getColumnDimension()) {this.ownerRegistry=this.addColsToRegistry(this.ownerRegistry);}
		if(this.owners.size()>=this.ownerRegistry.getRowDimension()) {this.ownerRegistry=this.addRowsToRegistry(this.ownerRegistry);}
		
		this.ownerRegistry.setEntry(agent_num, plot_num, 1d);
	}
	
	/**
	 * Sets an entry in the TenantRegistry. <br />
	 * @param p
	 * @param f The farmer object. If null, then the plot is not rent by anyone
	 */
	public void setTenantEntry(Plot p, Farmer f)  {
		//check if p in plots
 		if(! this.plots.contains(p)) this.plots.add(p);
		int plot_num = this.plots.indexOf(p);
		
		//check if we Need to increase the registry
		if(this.plots.size()>=this.tenantRegistry.getColumnDimension()) {this.tenantRegistry=this.addColsToRegistry(this.tenantRegistry);}
		
		
		//check if a in owners
		if(f==null) {
			this.tenantRegistry.setColumn(plot_num, new double[this.tenantRegistry.getColumnDimension()]);
		}else {
			if(! this.tenants.contains(f)) this.tenants.add(f);
			int agent_num = this.tenants.indexOf(f);
			
			if(this.tenants.size()>=this.tenantRegistry.getRowDimension()) {this.tenantRegistry=this.addRowsToRegistry(this.tenantRegistry);}
			
			this.tenantRegistry.setEntry(agent_num, plot_num, 1d);
		}
		
	}
	
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public  ArrayList<Plot> getOwnedPlots(Agent a) {
		ArrayList<Plot>r = new ArrayList<Plot>();
		
		int agent_num = this.owners.indexOf(a);
		double[] tmp = this.ownerRegistry.getRow(agent_num);
		for (int i = 0; i < plots.size(); i++) {
			if ( Double.compare(tmp[i], 0d)!=0) r.add(this.plots.get(i));
			
		}

	    return r;
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public  ArrayList<Plot> getRentedPlots(Farmer f) {
		ArrayList<Plot>r = new ArrayList<Plot>();
		
		int agent_num = this.tenants.indexOf(f);
		if(agent_num >= 0) {
			double tmp[] = this.tenantRegistry.getRow(agent_num);
			for (int i = 0; i < plots.size(); i++) {
				if (Double.compare(tmp[i], 0d)!=0) r.add(this.plots.get(i));
			}
		}

	    return r;
	}
	
	/**
	 * Returns the Plots that are available for a farmer to cultivate. <br />
	 * Those plots are composed of: (i) those that are rented by the farmer 
	 * (ii) Those  that are owned by the farmer and are not rented to third agents
	 * @param f Farmer
	 * @return ArrayList(Plot)
	 */
	public ArrayList<Plot> getCultivatingPlots(Farmer f) {
		ArrayList<Plot> r = new ArrayList<Plot>();
		
		//get rented
		r.addAll(this.getRentedPlots(f));
				
		//get owned but not rented to third farmers
		int agent_num = this.owners.indexOf(f);
		
		double tmp[] = this.ownerRegistry.getRow(agent_num);
		for (int i = 0; i < plots.size(); i++) {
			if (Double.compare(tmp[i], 0d)>0) {
				//now we deal with owned plots of the farmer
				//the question is: are they rented ?
				if(this.hasTenant(this.plots.get(i))) r.add(this.plots.get(i));
			}
					
		}
				
		return r;		
	}
	
	/**
	 * Checks whether ther is a tenant for the Plot
	 * @param p {@link Plot}
	 * @return true/false
	 */
	public boolean hasTenant(Plot p) {
		try {
			this.getTenant(p);
		}
		catch(NoSuchAgentException e) {
			return true;
		}
		return false;
	}



	/**
	 * 
	 * @param or
	 */
	public  void setOwnerRegistry(HashMap<Plot, Agent> or) {
		for (Map.Entry<Plot, Agent> entry : or.entrySet()) {
			this.setOwnerEntry(entry.getKey(), entry.getValue());
		}
		
	}

	/**
	 * 
	 * @param cultivatorRegistry
	 */
	public  void setTenantRegistry(HashMap<Plot, Farmer> or) {
		for (Map.Entry<Plot, Farmer> entry : or.entrySet()) {
			this.setTenantEntry(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * Gets the Agent that owns a Plot. <br />
	 * We assume that ownerRegistry secures that one Plot belongs only to one Agent.
	 * @param p
	 */
	public Agent getOwner(Plot p) throws NullPointerException {

		if (this.plots.contains(p)) {
			int plot_num = this.plots.indexOf(p);
			double tmp[] = this.ownerRegistry.getColumn(plot_num);
			for (int i = 0; i < tmp.length; i++) {
				if(Double.compare(tmp[i], 0d)!=0) return this.owners.get(i);
			}
		}
		
		throw new NullPointerException("There is no Owner for this Plot: "+p.toString()+" / This is a fatal error.");
	}
	
	/**
	 * Gets the Farmer that rents a Plot. <br />
	 * We assume that tenantRegistry secures that one Plot is rented by only one Agent.
	 * @param p
	 */
	public Farmer getTenant(Plot p) throws NoSuchAgentException {

		if (this.plots.contains(p)) {
			int plot_num = this.plots.indexOf(p);
			double tmp[] = this.tenantRegistry.getColumn(plot_num);
			for (int i = 0; i < tmp.length; i++) {
				if(Double.compare(tmp[i], 0d)!=0) return this.tenants.get(i);
			}
		}
		
		throw new NoSuchAgentException("There is no Tenant for this Plot");
	}
	
	/**
	 * Gets the Agent that will cultivate the Plot. <br />
	 * We assume that tenantRegistry secures that one Plot is rented by only one Agent.
	 * @param p
	 */
	public Agent getCultivator(Plot p) {

		if (this.plots.contains(p)) {
			int plot_num = this.plots.indexOf(p);
			double tmp[] = this.tenantRegistry.getColumn(plot_num);
			for (int i = 0; i < tmp.length; i++) {
				if(Double.compare(tmp[i], 0d)!=0) return this.tenants.get(i);
			}
		}
		return this.getOwner(p);
	}
	
	
	/**
	 * Updates the ownerValueLayer. <br />
	 * For each GridPoint, its value is set as the ID of the owner.
	 * @param vl
	 */
	public void updateOwnerValueLayer(GridValueLayer vl) {
		for (int j = 0; j < this.ownerRegistry.getColumnDimension(); j++) {
			for (int i = 0; i < this.ownerRegistry.getRowDimension(); i++) {
				if(this.ownerRegistry.getEntry(i, j)>0d) {
					ArrayList<GridPoint> gps=this.plots.get(j).getGridPoints();
					for (GridPoint gp : gps) {
						vl.set((this.owners.get(i).getID()).doubleValue(), gp.getX(),gp.getY());
					}
				}
			}			
		}
		
	}
	
	/**
	 * Updates the ownerValueLayer. <br />
	 * For each GridPoint, its value is set as the ID of the owner.
	 * @param vl
	 */
	public void updateTenantValueLayer(GridValueLayer vl) {
		for (int j = 0; j < this.tenantRegistry.getColumnDimension(); j++) {
			for (int i = 0; i < this.tenantRegistry.getRowDimension(); i++) {
				if(this.tenantRegistry.getEntry(i, j)>0d) {
					ArrayList<GridPoint> gps=this.plots.get(j).getGridPoints();
					for (GridPoint gp : gps) {
						vl.set((this.tenants.get(i).getID()).doubleValue(), gp.getX(),gp.getY());
					}					
				} //end if tenant is found
				break;
			}			
		}
		
	}
	
	/**
	 * Updates the cultivatorValueLayer. <br />
	 * For each GridPoint, its value is set as the ID of the owner.
	 * @param vl
	 */
	public void updateCultivatorValueLayer(GridValueLayer vl) {
		for (Plot p : this.plots) {
			//double v = this.g
		}
		
		for (int j = 0; j < this.tenantRegistry.getColumnDimension(); j++) {
			for (int i = 0; i < this.tenantRegistry.getRowDimension(); i++) {
				if(this.tenantRegistry.getEntry(i, j)>0d) {
					ArrayList<GridPoint> gps=this.plots.get(j).getGridPoints();
					for (GridPoint gp : gps) {
						vl.set((this.tenants.get(i).getID()).doubleValue(), gp.getX(),gp.getY());
					}					
				} //end if tenant is found
				break;
			}			
		}
		
	}
	
	
	
	@Override
	public String toString() {
		return "[" + super.toString() + "] " +
				"\nNumber of Distinct Plots: " + this.plots.size() +
				"\nNumber of Distinct Owners: " + this.owners.size() +
				"\nNumber of Distinct Tenants: " + this.tenants.size() +
				"\nSize of ownerRegistry: " + this.ownerRegistry.getRowDimension() + "x" + this.ownerRegistry.getColumnDimension() +
				"\nSize of tenantRegistry: " + this.tenantRegistry.getRowDimension() + "x" + this.tenantRegistry.getColumnDimension() +
				"\nownerRegistry: " + this.ownerRegistry.toString() +
				"\ntenantRegistry: " + this.tenantRegistry.toString()
		;
	}

	/**
	 * Increase the columns in a Registry 
	 * @param m
	 * @return
	 */
	private BlockRealMatrix addColsToRegistry(BlockRealMatrix m) {
		BlockRealMatrix m2 = new BlockRealMatrix(m.getRowDimension(), m.getColumnDimension()+LandPropertyRegistry.defaultNumOfPlots);
		m2.setSubMatrix(m.getData(), 0,0);
		return m2;		
	}
	
	/**
	 * Increase the rows in a Registry
	 * @param m
	 * @return
	 */
	private BlockRealMatrix addRowsToRegistry(BlockRealMatrix m) {
		BlockRealMatrix m2 = new BlockRealMatrix(m.getRowDimension()+LandPropertyRegistry.defaultNumOfAgents, m.getColumnDimension());
		m2.setSubMatrix(m.getData(), 0,0);
		return m2;			
	}
	
	
	
	

} //end class
