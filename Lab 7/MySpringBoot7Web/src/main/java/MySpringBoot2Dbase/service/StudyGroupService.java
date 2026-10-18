package MySpringBoot2Dbase.service;

import MySpringBoot2Dbase.dao.StudyGroupRepository;
import MySpringBoot2Dbase.entity.StudyGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyGroupService {
    private final StudyGroupRepository repository;

    public StudyGroupService(StudyGroupRepository repository) {
        this.repository = repository;
    }

    public List<StudyGroup> findAll() {
        return repository.findAll();
    }

    public Optional<StudyGroup> findById(Integer id) {
        return repository.findById(id);
    }

    public StudyGroup save(StudyGroup studyGroup) {
        return repository.save(studyGroup);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
