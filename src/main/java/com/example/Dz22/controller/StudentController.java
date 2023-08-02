package com.example.Dz22.controller;

import com.example.Dz22.model.Avatar;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.StudentRepository;
import com.example.Dz22.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student==null){
            return ResponseEntity.notFound().build();
        }return ResponseEntity.ok(student);
    }
    @GetMapping()
    public ResponseEntity<Collection<Student>> findStudentRange(@RequestParam int min, @RequestParam int max){
      return ResponseEntity.ok(studentService.findByAge(min, max));
    }
    @GetMapping("/faculty/{id}")
    public String findFaculty(@PathVariable long id){
        return studentService.findFacultyByStudentId(id).getName();
    }

//   @GetMapping("/faculty/{id}")
//    public String findFaculty(@PathVariable long id){
//        return studentService.findStudent(id).getFaculty().getName();
//   }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable  Long id){
        Student foundStudent = studentService.editStudent( student);
        if(foundStudent==null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping("{id}")
      public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
            if(avatar.getSize()>1024*300){
                return ResponseEntity.badRequest().body("Файл большой ");
            }studentService.uploadAvatar(id,avatar);
            return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            is.transferTo(os);
        }
    }
    @GetMapping("/WithA")
    public List<Student> studentsWithA(){
        List<Student> students = studentRepository.findAll().stream().filter(student -> student.getName().startsWith("A")).collect(Collectors.toList());
        return students;
    }

    @GetMapping("AvgAge")
    public Double studentAvgAge(){
        DoubleSummaryStatistics avg = studentRepository.findAll().stream().mapToDouble(Student:: getAge).summaryStatistics();
       return avg.getAverage();
    }








}
