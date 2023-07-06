package com.example.Dz22.service;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
}
