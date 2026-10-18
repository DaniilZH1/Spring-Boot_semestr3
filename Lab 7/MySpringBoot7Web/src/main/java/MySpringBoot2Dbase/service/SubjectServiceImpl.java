package MySpringBoot2Dbase.service;

import MySpringBoot2Dbase.dao.SubjectDao;
import MySpringBoot2Dbase.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> findAll() { return subjectDao.findAll(); }

    @Override
    public Optional<Subject> findById(Integer id) { return subjectDao.findById(id); }

    @Override
    public Subject create(Subject subject) {
        subject.setId(null);
        return subjectDao.save(subject);
    }

    @Override
    public Optional<Subject> update(Subject subject) {
        if (subject.getId() == null || !subjectDao.existsById(subject.getId())) {
            return Optional.empty();
        }
        return Optional.of(subjectDao.save(subject));
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!subjectDao.existsById(id)) return false;
        subjectDao.deleteById(id);
        return true;
    }
}
