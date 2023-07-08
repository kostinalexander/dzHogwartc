package com.example.Dz22.repository;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findByColorIgnoreCase(String color);




}
