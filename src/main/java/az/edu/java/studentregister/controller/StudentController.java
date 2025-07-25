package az.edu.java.studentregister.controller;

import az.edu.java.studentregister.model.dto.StudentDto;
import az.edu.java.studentregister.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentDto addStudent(@Valid @RequestBody StudentDto studentDto) {

        StudentDto newStudentDto = studentService.addStudent(studentDto);
        return newStudentDto;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {

        return studentService.getStudent(id);
    }

    @DeleteMapping("{id}")
    public void deleteStudentById(@PathVariable Long id) {

        studentService.deleteStudent(id);
    }

    @PutMapping("{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {

        return studentService.updateStudent(id, studentDto);
    }
}
