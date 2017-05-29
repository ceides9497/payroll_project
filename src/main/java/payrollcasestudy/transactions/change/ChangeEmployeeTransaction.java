package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {

    MemoryRepository database = MemoryRepository.globalPayrollDatabase;
    private int employeeId;

    public ChangeEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    public void execute() {
        Employee employee = database.getEmployee(employeeId);
        changeEmployee(employee);
    }

    public abstract void changeEmployee(Employee employee);
}
