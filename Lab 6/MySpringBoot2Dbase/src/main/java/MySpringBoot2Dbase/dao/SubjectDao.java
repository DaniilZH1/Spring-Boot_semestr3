package MySpringBoot2Dbase.dao;

import MySpringBoot2Dbase.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<Subject, Integer> {
}
