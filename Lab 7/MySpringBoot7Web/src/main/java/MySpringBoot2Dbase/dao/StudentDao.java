package MySpringBoot2Dbase.dao;

import MySpringBoot2Dbase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {
}
