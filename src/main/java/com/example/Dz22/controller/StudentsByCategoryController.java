package com.example.Dz22.controller;

import com.example.Dz22.model.Student;
import com.example.Dz22.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class StudentsByCategoryController {
    @Autowired
    private final StudentService studentService;

    public StudentsByCategoryController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/getAll")
    public Integer getAllStudents(){
        return studentService.countAllStudents();
    }
    @GetMapping("/getAVG")
    public Integer avgStudentsAge(){
        return studentService.AvgAgeStudent();
    }
    @GetMapping("/getFive")
    public List<Student> fiveLastStudents(){
        return studentService.fiveLastStudents();
    }

}
