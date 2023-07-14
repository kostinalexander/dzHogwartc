package com.example.Dz22.service;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.FacultyRepository;

import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty( Faculty faculty) {

        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }
    @Override
    public Collection<Student> findStudentsByFacultyId(long id){
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            return faculty.getStudents();
        }
        return null;
    }




}
