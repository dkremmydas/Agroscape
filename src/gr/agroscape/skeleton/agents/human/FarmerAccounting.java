package gr.agroscape.skeleton.agents.human;

/**
 * A base class for representing the financial status of a Farmer
 *  
 * @author Dimitris Kremmydas
 * @version $Revision$
 * @since 2.0
 */
public class FarmerAccounting {

	//assets
	private Long cash = 0l;
	
	private Long cropInventory = 0l;
	
	private Long machinery = 0l;
	
	
	// liabilities
	private Long debts = 0l;
	
	private Long equity = 0l;
	

	public void addCash(Long amount) {
		this.cash =+ amount;
	}
	
	public void removeCash(Long amount) {
		this.cash =- amount;
	}
	
	public void addCropInventory(Long amount) {
		this.cropInventory =+ amount;
	}
	
	public void removeCropInventory(Long amount) {
		this.cropInventory =- amount;
	}
	
	public void addMachinery(Long amount) {
		this.machinery =+ amount;
	}
	
	public void removeMachinery(Long amount) {
		this.machinery =- amount;
	}
	
	public void addDebt(Long amount) {
		this.debts =+ amount;
	}
	
	public void removeDebt(Long amount) {
		this.debts =- amount;
	}
	
	public void addEquity(Long amount) {
		this.equity =+ amount;
	}
	
	public void removeEquity(Long amount) {
		this.equity =- amount;
	}

	public Long getCash() {
		return cash;
	}

	public Long getCropInventory() {
		return cropInventory;
	}

	public Long getMachinery() {
		return machinery;
	}

	public Long getDebts() {
		return debts;
	}

	public Long getEquity() {
		return equity;
	}
	
	
	
}
