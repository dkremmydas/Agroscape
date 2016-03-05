package gr.agroscape.skeleton.agents.human;



/**
 * A Farmer agent of Agroscape. <p />
 * <p>This is the abstract class that gives some basic functionality:
 *
 * @author Dimitris Kremmydas
 * @version $Revision$
 * @since 1.0
  */
public class Farmer extends HumanAgent  {
	
	private FarmerAccounting account = new FarmerAccounting();


	public Farmer(int id) {
		super();
		this.setName(id);
	}
	
	public Farmer() {
		super();
	}

	
	public FarmerAccounting getAccount() {
		return account;
	}
	
	
	
	

} //end class
