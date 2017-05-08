import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.Employee;
import Presenters.EmployeePresenter;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		get("/", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/hello.vm");
		    }, new VelocityTemplateEngine());
		
		get("/NewEmployee", (request, response) -> {
			 		      return new ModelAndView(new HashMap(), "templates/newEmployee.vm");
			 		    }, new VelocityTemplateEngine());
     /*   post("/createHourly", (request, response) -> {
			
			EmployeeController.createNewEmployeeHourly(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("hourly"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());*/
        
   	
		post("/newEmployee", (request, response) -> {		
			 EmployeePresenter.registrar_empleado(request.queryParams("kind"),request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario"),request.queryParams("comision"));
			 response.redirect("/Employees");
	         return new ModelAndView(new HashMap(), "templates/indexEmployee.vm");
		}, new VelocityTemplateEngine());
			

		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/Employees", (request, response) -> {
			view.put("employees", EmployeePresenter.Devolver_empleados());
		      return new ModelAndView(view, "templates/indexEmployee.vm");
		    }, new VelocityTemplateEngine());

	}
}
