package my.nmk.sb1.jpa.repositories;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import my.nmk.sb1.objects.Employee;

@Component
public interface EmployeesRepository extends CrudRepository<Employee, Long> {
//	List<Employee> findByLastName(String lastName);
	Optional<Employee> findById(Long id);
}
