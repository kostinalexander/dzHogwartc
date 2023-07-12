package com.example.Dz22.repository;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findByColorIgnoreCase(String color);






//    @Query("SELECT s FROM Student s WHERE s.faculty.id = :facultyId")
//    List<Student> findStudentsByFacultyId(@Param("facultyId") Long facultyId);



}
