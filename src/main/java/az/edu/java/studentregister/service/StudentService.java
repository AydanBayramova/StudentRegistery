package az.edu.java.studentregister.service;

import az.edu.java.studentregister.dao.entity.StudentEntity;
import az.edu.java.studentregister.dao.repository.StudentRepository;
import az.edu.java.studentregister.exception.IllegalAgeException;
import az.edu.java.studentregister.mapper.StudentMapper;
import az.edu.java.studentregister.model.dto.StudentDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class StudentService {

   private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto addStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.studentDtoToEntity(studentDto);
        if (studentEntity.getAge()<18){
            throw new IllegalAgeException("Age is under 18");
        }
        StudentEntity save = studentRepository.save(studentEntity);
        return studentMapper.entityToDto(save);
    }

    public void deleteStudent(Long id) {

        studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.deleteById(id);
    }

    public List<StudentDto> getAllStudents() {

        List<StudentEntity> all = studentRepository.findAll();
        List<StudentDto> studentDtos = studentMapper.entityListToDtoList(all);
        return studentDtos;
//        studentRepository.findAll().stream().forEach(studentEntity -> studentMapper.entityToDto(studentEntity));
    }

    public StudentDto getStudent(Long id) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.entityToDto(studentEntity);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentEntity.setName(studentDto.getName());
        StudentEntity save = studentRepository.save(studentEntity);
        return studentMapper.entityToDto(save);
    }
}
