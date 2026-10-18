package MySpringBoot2Dbase.service;

import MySpringBoot2Dbase.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Integer id);
    Student create(Student student);
    Optional<Student> update(Student student);
    boolean deleteById(Integer id);
}
