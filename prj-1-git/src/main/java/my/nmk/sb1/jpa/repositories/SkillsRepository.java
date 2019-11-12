package my.nmk.sb1.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import my.nmk.sb1.objects.Skill;

public interface SkillsRepository extends CrudRepository<Skill, Long> {
	
}
