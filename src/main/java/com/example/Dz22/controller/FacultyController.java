package com.example.Dz22.controller;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.FacultyRepository;
import com.example.Dz22.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/faculty")
public class FacultyController {


    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;

    @Autowired
    public  FacultyController(FacultyService facultyService, FacultyRepository facultyRepository){
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFaculty(id);
        if(faculty==null){
            return ResponseEntity.notFound().build();
        }return ResponseEntity.ok(faculty);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFacultyForColor(@RequestParam String color){
        return ResponseEntity.ok(facultyService.findByColor(color));
    }

//    @GetMapping("/faculty/{id}")
//    public Collection<Student> findFaculty(@PathVariable long id) { // метод для нахождения всех студентов факультета
//        return facultyService.findFaculty(id).getStudents();
//    }
@GetMapping("/faculty/{id}")
public Collection<Student> findFaculty(@PathVariable long id) { // метод для нахождения всех студентов факультета
    return facultyService.findStudentsByFacultyId(id);
}


    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty ){
        return facultyService.addFaculty(faculty);
    }
    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty,@PathVariable Long id)  {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if(foundFaculty==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/veryLongName")
    public String longNameFaculty(){
      return facultyService.longNameFaculty();
    }

    @GetMapping("/exampleTask")
    public int example(){
         return facultyService.example();
        // Задание 4, как я понял, чтобы работало быстрее надо сделать стрим парарлельным
    }
}
