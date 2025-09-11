package az.edu.java.studentregister.dao.repository;

import az.edu.java.studentregister.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findByNameContainingIgnoreCase(String name);

    List<StudentEntity> findByNameContainingIgnoreCaseAndFaculty(String name, String faculty);

}
