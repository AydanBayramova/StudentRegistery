package az.edu.java.studentregister.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDto {

    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String faculty;
    private String phoneNumber;

}
