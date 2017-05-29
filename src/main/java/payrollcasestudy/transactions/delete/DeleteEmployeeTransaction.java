package payrollcasestudy.transactions.delete;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.transactions.Transaction;

public class DeleteEmployeeTransaction implements Transaction{
    private int employeeId;

    public DeleteEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    public void execute() {
        MemoryRepository database = MemoryRepository.globalPayrollDatabase;
        database.deleteEmployee(employeeId);
    }
}
