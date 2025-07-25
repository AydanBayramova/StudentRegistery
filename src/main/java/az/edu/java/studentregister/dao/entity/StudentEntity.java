package az.edu.java.studentregister.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "STUDENT_REGISTER")
@Getter
@Setter
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = false, nullable = false, length = 50)
    private String name;

    @Column(unique = false, nullable = false, length = 50)
    private String lastName;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Transient
    private long age;

    public long getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
