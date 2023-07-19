package com.example.Dz22.service;

import com.example.Dz22.model.Avatar;
import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent( Student student);
    void deleteStudent(long id);
    public Collection<Student> findByAge(int min, int max);
    Faculty findFacultyByStudentId(Long studentId);
    public Avatar findAvatar(long studentId);
    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Integer countAllStudents();
    Integer AvgAgeStudent();
    List<Student> fiveLastStudents();

    List<Avatar> allAvatars(Integer pageNumber, Integer pageSize);
}
