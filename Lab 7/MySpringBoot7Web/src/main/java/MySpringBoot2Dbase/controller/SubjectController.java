package MySpringBoot2Dbase.controller;

import MySpringBoot2Dbase.entity.Subject;
import MySpringBoot2Dbase.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> findAll() { return subjectService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> findById(@PathVariable Integer id) {
        return subjectService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OperationResult<Subject>> create(@RequestBody Subject subject) {
        Subject created = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResult.success("Дисциплина успешно создана", created));
    }

    @PutMapping
    public ResponseEntity<OperationResult<Subject>> update(@RequestBody Subject subject) {
        return subjectService.update(subject)
                .map(updated -> ResponseEntity.ok(OperationResult.success("Дисциплина успешно обновлена", updated)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(OperationResult.failure("Дисциплина с таким id не найдена")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Void>> delete(@PathVariable Integer id) {
        if (!subjectService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(OperationResult.failure("Дисциплина с таким id не найдена"));
        }
        return ResponseEntity.ok(OperationResult.success("Дисциплина успешно удалена", null));
    }
}
