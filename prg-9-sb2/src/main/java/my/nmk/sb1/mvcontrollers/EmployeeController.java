package my.nmk.sb1.mvcontrollers;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.nmk.sb1.jpa.repositories.EmployeesRepository;
import my.nmk.sb1.objects.Employee;
import my.nmk.sb1.objects.EmployeeService;

@Controller
public class EmployeeController {
	private final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	@Autowired
	EmployeeService employeeService;
    @Autowired
    EmployeesRepository employeeRepository;
	
	@GetMapping({"/employee/{action}", "/employee/{action}/{optional}"})
	public String getActions(
			@PathVariable String action, 
			@PathVariable(required = false) Optional<String> optional,
			Map<String, Object> model) {
		logger.debug("Employee' get action: " + action + ", optional: " + (optional.isPresent()? optional.get(): "null"));
		switch(action) {
		case "edit":
			long idOfEmployee = Integer.parseInt(optional.get());
			Employee employee = null;
			try {
				employee = employeeService.getEmployee(idOfEmployee);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			model.put("employee", employee);
			break; 
		case("del"):
			if(optional.isPresent()) {
				logger.debug("We going to delete employee: " + optional.get());
				employeeService.deleteEmployee(Integer.parseInt(optional.get()));
				model.put("message", "Employee successfully deleted!");
			} else {
				model.put("message", "Employee ID not for deletion!");
			}
			return "forward:/";
		}
		return "employee-form";
	}
	
	@PostMapping({"/employee/{action}", "/employee/{action}/{optional}"})
	public String postActions(
			@PathVariable String action, 
			@PathVariable(required = false) Optional<String> optional,
			@RequestParam Map<String,String> postParams,
			Map<String, String> model) {
		logger.debug("Employee's post action: " + action);
		for(String key: postParams.keySet()) {
			logger.debug("Post key/value: " + key + "/" + postParams.get(key));
		}
		switch(action) {
		case("new"):
		case("edit"):
			if(Employee.validateDataMap(postParams)) {
				Employee employee = new Employee(postParams);
				employeeRepository.save(employee);
				logger.debug("The employee saved for action: " + action.toUpperCase());
				model.put("message", "Employee saved!");
			} else {
				logger.debug("Invalid data to add new employee!");
				model.put("message", "Invalid data to add new employee!");
			}
			break;
		case("del"):
			if(optional.isPresent()) {
				logger.debug("We going to delete employee: " + optional.get());
				employeeService.deleteEmployee(Integer.parseInt(optional.get()));
				model.put("message", "Employee successfully deleted!");
			} else {
				model.put("message", "Employee ID not for deletion!");
			}
		}
		return "forward:/";
//		return "redirect:/";
	}

}
