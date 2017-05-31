package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import payrollcasestudy.entities.PayCheck;

public interface Affiliation {
	double calculateDeduction(PayCheck payCheck);
	Affiliation setAffiliation();
}
