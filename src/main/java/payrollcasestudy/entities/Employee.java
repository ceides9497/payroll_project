package payrollcasestudy.entities;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.entities.affiliations.Affiliation;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentmethods.PaymentMethod;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Employee {
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private int employeeId;
    private String name;
    private String address;
    private Affiliation affiliations[];

    public Employee (){
    	
    }
    public Employee(int employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        affiliations = null;
    }
    public int getEmployeeId(){
    	return employeeId;
    }
    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPayDate(Calendar payDate) {
        return paymentSchedule.isPayDate(payDate);
    }

    public Calendar getPayPeriodStartDay(Calendar payDate) {
        return paymentSchedule.getPayPeriodStartDate(payDate);
    }

    public void payDay(PayCheck payCheck) {
        double grossPay = paymentClassification.calculatePay(payCheck);
    	double deduction  = 0;
    	for(Affiliation affiliation: affiliations){
    		deduction = deduction + affiliation.calculateDeduction(payCheck);
    	}    			
        double netPay = grossPay - (deduction);
        payCheck.setGrossPay(grossPay);
        payCheck.setNetPay(netPay);
        payCheck.setDeductions(deduction);
        paymentMethod.pay(payCheck);
    }

	public void seAllAffiliations(UnionAffiliation unionAffiliation) {
		for (Affiliation affiliation: affiliations) {
				affiliation.setAffiliation();
	      }
	}

	public void safeEmployeeInDB(int id, Employee employee){
		MemoryRepository.globalPayrollDatabase.addEmployee(id, employee);
	}
	
	public static Employee getEmployeeFromDB(int employeeId){
		return MemoryRepository.globalPayrollDatabase.getEmployee(employeeId);
	}
	
	public String getClasificationPayment()
	 {
	  		if(paymentClassification instanceof HourlyPaymentClassification)
	 			return "Hora";	
	 		if(paymentClassification instanceof SalariedClassification)
	 			return "Salario";
	 		if(paymentClassification instanceof CommissionedPaymentClassification)
	 			return "Comision";	
	 		return "";
	 }	
}
