package my.nmk.sb1.objects;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Skills")
public class Skill {
	
	public Skill() {};
	
	public Skill(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(mappedBy = "skills")
	Set<Employee> employees = new HashSet<Employee>();
	
	
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	@Id
	@GeneratedValue
	private Long Id;
	private String name;
	private String description;
	
	@Override
	public String toString() {
		return String.format("Skill[id=%d, name='%s', description='%s']", 
				Id, name, description);
	}
}
