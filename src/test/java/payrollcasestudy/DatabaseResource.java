package payrollcasestudy;

import org.junit.rules.ExternalResource;
import payrollcasestudy.boundaries.MemoryRepository;

public class DatabaseResource extends ExternalResource {
    protected MemoryRepository instance;

    public void before(){
        instance = MemoryRepository.globalPayrollDatabase;
    }

    public void after(){
        instance.clear();
    }

    public MemoryRepository getInstance() {
        return instance;
    }
}
