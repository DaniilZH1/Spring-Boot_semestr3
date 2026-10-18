package MySpringBoot2Dbase.service;

import MySpringBoot2Dbase.dao.StudentDao;
import MySpringBoot2Dbase.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() { return studentDao.findAll(); }

    @Override
    public Optional<Student> findById(Integer id) { return studentDao.findById(id); }

    @Override
    public Student create(Student student) {
        student.setId(null);
        return studentDao.save(student);
    }

    @Override
    public Optional<Student> update(Student student) {
        if (student.getId() == null || !studentDao.existsById(student.getId())) {
            return Optional.empty();
        }
        return Optional.of(studentDao.save(student));
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!studentDao.existsById(id)) return false;
        studentDao.deleteById(id);
        return true;
    }
}
