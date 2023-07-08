package com.example.Dz22.service;

import com.example.Dz22.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent( Student student);
    void deleteStudent(long id);
    public Collection<Student> findByAge(int min, int max);
}
