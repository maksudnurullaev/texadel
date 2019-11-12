package my.nmk.sb1.objects;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import my.nmk.sb1.jpa.repositories.EmployeesRepository;
import my.nmk.sb1.jpa.repositories.SkillsRepository;

@Controller
public class EmployeeService {
	private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    //List<Employee> employeeList = EmployeeList.getInstance();
    
    @Autowired
    EmployeesRepository employeeRepository;
    @Autowired
    SkillsRepository skillsRepositiry;

	public Iterable<Employee> getAllEmployees() {    
    	Assert.notNull(employeeRepository, "EmployeeRepository instance should not be NULL!");
//    	employeeRepository.find
    	return employeeRepository.findAll();
    }

    public void setInitialData() {
    	Assert.notNull(employeeRepository, "EmployeeRepository object should not be NULL!");
    	Assert.notNull(skillsRepositiry, "SkillsRepository instance should not be NULL!");
    	if(employeeRepository.count() == 0L) {
    		employeeRepository.saveAll(EmployeeList.getInstance());
    	}
    	if(skillsRepositiry.count() == 0L) {
    		skillsRepositiry.saveAll(Arrays.asList(
    				new Skill("C++", "Old fashioned OOP language!"),
    				new Skill("Perl", "Old fashioned script language!"),
    				new Skill("Java", "Modern OOP language!"))
    		);
    	}
    }
    
    public List<Employee> searchEmployeesByName(String name) {
        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getName)
                                                    .thenComparing(Employee::getLastName);
        List<Employee> result = StreamSupport.stream(employeeRepository.findAll().spliterator(), false) //TODO I'm not sure about optimal version of searching, it could be done in DB side
                .filter(e -> e.aString().toUpperCase().contains(name.toUpperCase()))
                .sorted(groupByComparator)
                .collect(Collectors.toList());
        return result;
    }

    public Employee getEmployee(long id) throws Exception {
    	logger.debug("Try to get employee by id: " + id); 
    	if(employeeRepository.existsById(id)) {
        	logger.debug("Found employee by id: " + id); 
        	Optional<Employee> result = employeeRepository.findById(id);
        	if(result.isPresent()) {
        		return result.get();
        	} else {
	        	logger.warn("Not found employee by id: " + id); 
	            throw new Exception("The Employee id " + id + " not found");
        	}
        }
    	return null;
    }  
    
    public long saveEmployee(Employee employee) {
    	employeeRepository.save(employee);
    	return employee.getId();
    }
    
//    public boolean updateEmployee(Employee employee) {
//    	employeeRepository.save(employee);
//    	return true;
////    	int matchIdx = 0;
////    	
////    	Optional<Employee> match = employeeRepository.findById(employee.getId());
//////    			employeeList.stream()
//////    			.filter(e -> e.getId() == employee.getId())
//////    			.findFirst();
////    	if(match.isPresent()) {
////    		matchIdx = employeeList.indexOf(match.get());
////    		employeeList.set(matchIdx, employee);
////    	}
////    	return match.isPresent();
//    }
    
    public void deleteEmployee(long id) {
    	employeeRepository.deleteById(id);
    }

}
