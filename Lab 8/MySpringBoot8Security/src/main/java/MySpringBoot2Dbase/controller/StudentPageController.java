package MySpringBoot2Dbase.controller;

import MySpringBoot2Dbase.dao.StudentDao;
import MySpringBoot2Dbase.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentPageController {
    private final StudentDao studentDao;

    public StudentPageController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentDao.findAll());
        return "list-students";
    }

    @GetMapping("/addStudentForm")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentDao.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam Integer studentId, Model model) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        model.addAttribute("student", student);
        return "add-student-form";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Integer studentId) {
        studentDao.deleteById(studentId);
        return "redirect:/list";
    }
}
