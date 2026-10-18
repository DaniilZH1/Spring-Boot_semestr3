package MySpringBoot2Dbase.service;

import MySpringBoot2Dbase.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> findAll();
    Optional<Subject> findById(Integer id);
    Subject create(Subject subject);
    Optional<Subject> update(Subject subject);
    boolean deleteById(Integer id);
}
