package MySpringBoot2Dbase.repository;

import MySpringBoot2Dbase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> { }
