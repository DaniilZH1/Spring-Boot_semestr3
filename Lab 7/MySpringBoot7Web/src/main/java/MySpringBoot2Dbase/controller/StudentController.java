package MySpringBoot2Dbase.controller;

import MySpringBoot2Dbase.entity.Student;
import MySpringBoot2Dbase.service.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() { return studentService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Integer id) {
        return studentService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OperationResult<Student>> create(@RequestBody Student student) {
        Student created = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResult.success("Студент успешно создан", created));
    }


    @PutMapping
    public ResponseEntity<OperationResult<Student>> update(@RequestBody Student student) {
        return studentService.update(student)
                .map(updated -> ResponseEntity.ok(OperationResult.success("Студент успешно обновлён", updated)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(OperationResult.failure("Студент с таким id не найден")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Void>> delete(@PathVariable Integer id) {
        if (!studentService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(OperationResult.failure("Студент с таким id не найден"));
        }
        return ResponseEntity.ok(OperationResult.success("Студент успешно удалён", null));
    }
}
