package my.nmk.sb1.objects;

import java.util.Map;
//import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

import my.nmk.sb1.annotations.Described;

@Described
@Entity
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
    
//    private static final AtomicLong counter = new AtomicLong(100);
    
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
    	if(StringUtils.isNumeric(data.get("id"))) {
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
