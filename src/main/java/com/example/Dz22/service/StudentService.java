package com.example.Dz22.service;

import com.example.Dz22.model.Student;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent( Student student);
    void deleteStudent(long id);
}
