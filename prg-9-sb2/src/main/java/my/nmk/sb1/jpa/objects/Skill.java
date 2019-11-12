package my.nmk.sb1.jpa.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
	
	public Skill() {};
	
	public Skill(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Long getID() {
		return ID;
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
	
	@Id
	@GeneratedValue
	private Long ID;
	private String name;
	private String description;
	
	@Override
	public String toString() {
		return String.format("Skill[id=%d, name='%s', description='%s']", 
				ID, name, description);
	}
}
