package com.example.Dz22.repository;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(int min, int max);



}
