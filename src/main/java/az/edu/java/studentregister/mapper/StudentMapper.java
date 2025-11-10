package az.edu.java.studentregister.mapper;

import az.edu.java.studentregister.dao.entity.StudentEntity;
import az.edu.java.studentregister.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper{

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    StudentEntity studentDtoToEntity(StudentDto studentDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    StudentDto entityToDto(StudentEntity studentEntity);

    List<StudentDto> entityListToDtoList(List<StudentEntity> studentEntities);

    List<StudentEntity> dtoListToEntityList(List<StudentDto> studentDtos);
}
