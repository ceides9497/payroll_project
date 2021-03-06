package Presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	
	public static void registrar_empleado(String tipo, String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision){
		int tipoInt = Integer.parseInt(tipo);
		if(tipoInt == 2){
			 registrar_empleado_por_hora(nombre_empleado, direccion_empleado, ci_employee, amount);
		}
		if(tipoInt == 1){
			if ( (comision == null) || (comision.equals("")) )
			{
			  registrar_empleado_Asalariado(nombre_empleado, direccion_empleado, ci_employee, amount);

			}
			else
			{
			 registrar_empleado_Asalariado_con_Comision(nombre_empleado, direccion_empleado, ci_employee, amount,comision);
			}
		}
	}
	
	public static void registrar_empleado_Asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	}
	
	public static void registrar_empleado_Asalariado_con_Comision(String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		double comisionn= Double.parseDouble(comision);
		 Transaction addEmployeeTransaction =
	                new AddCommissionedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt,comisionn);
	        addEmployeeTransaction.execute();
	}
	
	public static void registrar_empleado_por_hora(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddHourlyEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	}


	public static List<Employee> Devolver_empleados() {		
		return MemoryRepository.globalPayrollDatabase.getAllEmployees();
	}

	public static Employee getEmployee(int employe_ci) {
		return MemoryRepository.globalPayrollDatabase.getEmployee(employe_ci);
	}


}
