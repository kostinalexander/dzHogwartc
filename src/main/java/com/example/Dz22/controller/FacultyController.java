package com.example.Dz22.controller;

import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import com.example.Dz22.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;
    public  FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
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

    @GetMapping("/faculty/id")
    public Collection<Student> findFaculty(@PathVariable long id) { // метод для нахождения всех студентов факультета
        return facultyService.findFaculty(id).getStudents();
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty ){
        return facultyService.addFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty){
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
}
