package gr.agroscape.authorities;


import gr.agroscape.crops.Crop;

import java.util.HashMap;

/**
 * This class will act as the National Payment Authority. It is responsible for 
 * providing information regarding Policy Measures.<br />
 * It is responsible for:
 * <ul>
 * <li>it provides the Single Payment Value for a farmer</li>
 * <li>it provides the couple payments per crop</li>
 * <li>it keeps records on the subsidies per farmer</li>
 * </ul>
 * @author jkr
 *
 */
public class PaymentAuthority {
	
	//In euro/h
	private long singlePaymentValue = 2000;
	
	private HashMap<Crop,Long> coupledPayments = new HashMap<Crop, Long>();
	
	
	public long getSinglePaymentValue() {
		return singlePaymentValue;
	}


	public HashMap<Crop, Long> getCoupledPayments() {
		return coupledPayments;
	}


	public void setCoupledPayments(HashMap<Crop, Long> coupledPayments) {
		this.coupledPayments = coupledPayments;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | Single Payment Value: " + this.singlePaymentValue
				+ " | Coupled Payments: " + this.coupledPayments.toString();
	}
	
	
	
	
}
