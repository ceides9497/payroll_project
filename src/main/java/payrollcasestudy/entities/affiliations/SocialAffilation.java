package payrollcasestudy.entities.affiliations;

import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public class SocialAffilation implements Affiliation {
	public  final static  SocialAffilation NO_AFFILIATION = new SocialAffilation(0);
	private int memberId;
	private double dues;

	public SocialAffilation(int memberId) {
		this.memberId = memberId;
	}
	public int getMemberId() {
		return memberId;
	}
	
	public double calculateDeduction(PayCheck payCheck) {
		// TODO Auto-generated method stub
		return payCheck.getGrossPay() *0.12;
	}
	

}
