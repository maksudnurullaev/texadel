package my.nmk.sb1.objects;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import my.nmk.sb1.annotations.Described;

@Described
@Entity
@Table(name = "Employees")
public class Employee {
	@Id
	@GeneratedValue
    private long id;
    private String name;
    private String lastName;
    private String birthDate;
    private String role;
    private String department;
    private String email;

    @ManyToMany
	@JoinTable(name="employees_and_skills", 
		joinColumns = {@JoinColumn(name = "employee_id")},
		inverseJoinColumns = {@JoinColumn(name = "skill_id")})    
    Set<Skill> skills = new HashSet<Skill>();
    
    public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	public boolean isContainSkill(Long skillId) {
		return getSkills().stream().anyMatch(e -> e.getId() == skillId);
	}
	
	public boolean haSkills() {
		return skills.size() > 0;
	}
	
	public String skillsAsString() {
		return skills.stream().map(e -> e.getName()).collect(Collectors.joining(", "));
	}

	public Employee() {}
    
    private static final String[] validateFields = {"name", "lastName", "birthDate", "role", "department", "email"};
    
    public static boolean validateDataMap(Map<String,String> data) {
    	for(String key: validateFields) {
    		if(!data.containsKey(key)) return false;
    	}
    	return true;
    }
    
    public Employee(Map<String,String> data) {
    	this.name = data.get("name");
    	this.lastName = data.get("lastName"); 
    	this.birthDate = data.get("birthDate"); 
    	this.role = data.get("role"); 
    	this.department = data.get("department"); 
    	this.email = data.get("email");
    	if(!StringUtils.isEmpty(data.get("id"))) {
    		this.id = Long.parseLong(data.get("id"));
    	}
    }
//    
//    public Employee(String name, String lastName, String birthDate, String role, String department, String email, long id) {
//        this.name = name;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.role = role;
//        this.department = department;
//        this.email = email;     
//        this.id = id;
//    }
//    
    public Employee(String name, String lastName, String birthDate, String role, String department, String email) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;     
//        this.id = counter.incrementAndGet();
    }
//
    public long getId() {
        return id;
    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
    public String getName() {
        return this.name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
    public String getLastName() {
        return lastName;
    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
    public String getBirthDate() {
        return birthDate;
    }
//
//    public void setBirthDate(String birthDate) {
//        this.birthDate = birthDate;
//    }
//
    public String getRole() {
        return role;
    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
    public String getDepartment() {
        return department;
    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
    public String getEmail() {
        return email;
    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + 
                ", lastName=" + lastName + ", birthDate=" + birthDate + 
                ", role=" + role + ", department=" + department + 
                ", email=" + email + '}';
    }
    
    public String aString() {
    	return  name +
			    lastName +
			    birthDate +
			    role +
			    department +
			    email +     
			    id;
    }
}
