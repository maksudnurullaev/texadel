package my.nmk.sb1.mvcontrollers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.nmk.sb1.objects.Employee;
import my.nmk.sb1.objects.EmployeeService;

@Controller
public class WelcomeController {
	//EmployeeService employeeService = new EmployeeService();
	
	@Autowired
	EmployeeService employeeService = null;
	
	private final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@RequestMapping("/") 
	public String welcome(
			@RequestParam(name = "searchAction", required = false) String searchAction, 
			@RequestParam(name = "employeeName", required = false) String employeeName, 
			Map<String, Object> model) {
		Iterable<Employee> list = null;
		if( searchAction == null ) {
			logger.debug("Action, searchAction: " + searchAction + ", employeeName: " + employeeName);
			list = employeeService.getAllEmployees();
		} else if(searchAction.equalsIgnoreCase("searchByName")) {
			logger.debug("Action, searchByName: " + searchAction + ", employeeName: " + employeeName);
			list = employeeService.searchEmployeesByName(employeeName);
		}
		model.put("employeeList", list);			
		return "index";
	}

}
