package gr.agroscape.authorities;



import java.util.HashMap;

/**
 * This class will act as the National Payment Authority. It is responsible for 
 * providing information regarding Policy Measures.<br />
 * It is responsible for:
 * <ul>
 * <li>it provides the Single Payment Value for a farmer</li>
 * <li>it provides the couple payments per crop</li>
 * <li>it keeps records on the subsidies per farmer</li>
 * <li>//TODO it keeps records of the history of production decisions of {@link ICropProducer}s</li>
 * </ul>
 * @author Dimitris Kremmydas
 *
 */
public class PaymentAuthority {
	
	//In euro/h
	private long singlePaymentValue = 2000;
	
	private HashMap<ArableCropCultivation,Long> coupledPayments = new HashMap<ArableCropCultivation, Long>();
	
	
	public long getSinglePaymentValue() {
		return singlePaymentValue;
	}


	public HashMap<ArableCropCultivation, Long> getCoupledPayments() {
		return coupledPayments;
	}


	public void setCoupledPayments(HashMap<ArableCropCultivation, Long> coupledPayments) {
		this.coupledPayments = coupledPayments;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | Single Payment Value: " + this.singlePaymentValue
				+ " | Coupled Payments: " + this.coupledPayments.toString();
	}
	
	
	
	
}
