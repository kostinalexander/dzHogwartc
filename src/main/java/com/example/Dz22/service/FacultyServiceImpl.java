package com.example.Dz22.service;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.FacultyRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Request add faculty ");
         facultyRepository.save(faculty);
         logger.info("Faculty created" + faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty( Faculty faculty) {
        logger.info("Request faculty edit");
        facultyRepository.save(faculty);
        logger.info("Faculty edition"+ faculty);
        return faculty;
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Request delete faculty");
        facultyRepository.deleteById(id);
        logger.info("Faculty with id =  "+id + "deleted");
    }

    @Override
    public Collection<Faculty> findByColor(String color) {
        logger.info("Request find faculty by color");
        Collection<Faculty> faculties =  facultyRepository.findByColorIgnoreCase(color);
        logger.info("Faculties by color - "+faculties);
         return faculties;
    }
    @Override
    public Collection<Student> findStudentsByFacultyId(long id){
        logger.info("Request find students by faculty");
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            logger.info("Students by faculties - "+ faculty.getStudents());
            return faculty.getStudents();
        }
        return null;
    }

    @Override
    public String longNameFaculty() {
        Optional<Faculty> names =  facultyRepository.findAll().stream().min(Comparator.comparing(Faculty::getName));
        return names.get().getName();
    }

    @Override
    public int example() {
        int sum1 = Stream.iterate(1, a -> a + 1).parallel().limit(1_000_000).reduce(0, Integer::sum);
        return sum1;
    }




}
