package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	MemoryRepository database = MemoryRepository.globalPayrollDatabase;
	private int employeeId;
	private Employee employee;

	public ChangeNoMemberTransaction(int employee) {
		super(employee);
	}

	@Override
	public void changeEmployee(Employee employee) {
		int memberId = employee.getUnionAffiliation().getMemberId();
		employee.setUnionAffiliation(UnionAffiliation.NO_AFFILIATION);
		database.deleteUnionMember(memberId);
	}
}
