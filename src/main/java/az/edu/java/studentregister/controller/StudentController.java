package az.edu.java.studentregister.controller;

import az.edu.java.studentregister.dao.entity.StudentEntity;
import az.edu.java.studentregister.model.dto.StudentDto;
import az.edu.java.studentregister.service.StudentService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/search")
    public List<StudentDto> getStudentsByName(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/search/name-faculty")
    public List<StudentDto> searchByNameAndFaculty(
            @RequestParam String name,
            @RequestParam String faculty) {
        return studentService.getStudentsByNameAndFaculty(name, faculty);
    }

    @GetMapping("/students/export/excel")
    public void exportStudentsToExcel(HttpServletResponse response) throws IOException {
        // Response header
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");

        List<StudentDto> students = studentService.getAllStudents();

        // Excel workbook yarat
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Students");

        int rowCount = 0;

        // Header row
        Row header = sheet.createRow(rowCount++);
        header.createCell(0).setCellValue("LastName");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Faculty");
        header.createCell(3).setCellValue("PhoneNumber");

        // Data rows
        for (StudentDto student : students) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(student.getLastName());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getFaculty());
            row.createCell(3).setCellValue(student.getPhoneNumber());
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
