package com.example.Dz22.repository;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(int min, int max);




//    @Query("SELECT s FROM Student s WHERE s.faculty.id = :facultyId") // JPQL / SQL
//    List<Student> findStudentsByFacultyId(@Param("facultyId") Long facultyId);



//    @Query("SELECT s.faculty FROM Student s WHERE s.id = :studentId") // JPQL / SQL
//    Faculty findFacultyByStudentId(@Param("studentId") Long studentId);


}
