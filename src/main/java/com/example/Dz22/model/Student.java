package com.example.Dz22.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
//@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue // Auto, Identity, Sequence, Table
    private long id;
    private String name;
    private int age;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

//    public Student(){}
//
//    public Faculty getFaculty() {
//        return faculty;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(faculty, student.faculty);
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", faculty=" + faculty +
//                '}';
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, age, faculty);
//    }
//
//    public void setFaculty(Faculty faculty) {
//        this.faculty = faculty;
//    }
//
//    public Student(long id, String name, int age){
//        this.id = id;
//        this.name = name;
//        this.age = age;
//
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        this.age = age;
//    }
}



