package com.example.Dz22.repository;


import com.example.Dz22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT count(*)FROM student;",nativeQuery = true)
     Integer countStudentsAll();

    @Query(value = "SELECT avg(age) FROM student;",nativeQuery = true)
    Integer avgAgeStudent();

    @Query(value = "SELECT * FROM student ORDER BY student DESC LIMIT 5", nativeQuery = true)
    List<Student> fiveLastStudents();




//    @Query("SELECT s FROM Student s WHERE s.faculty.id = :facultyId") // JPQL / SQL
//    List<Student> findStudentsByFacultyId(@Param("facultyId") Long facultyId);



//    @Query("SELECT s.faculty FROM Student s WHERE s.id = :studentId") // JPQL / SQL
//    Faculty findFacultyByStudentId(@Param("studentId") Long studentId);


}
