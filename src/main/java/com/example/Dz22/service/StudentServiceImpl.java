package com.example.Dz22.service;

import com.example.Dz22.model.Student;
import com.example.Dz22.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

       return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent( Student student) {
        
        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
         studentRepository.deleteById(id);
    }
}
