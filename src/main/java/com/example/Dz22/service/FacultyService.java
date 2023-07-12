package com.example.Dz22.service;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(long id);
    Collection<Faculty> findByColor(String color);

    Collection<Student> findStudentsByFacultyId(long id);


}
