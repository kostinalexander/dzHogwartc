package com.example.Dz22;

import com.example.Dz22.controller.StudentController;
import com.example.Dz22.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests2 {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testLoads(){
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudent()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/student",String.class))
                .isNotNull();
    }

    @Test
    public void testCreateStudentAndDelete() throws Exception{
        Student student = new Student();
        student.setId(1);
        student.setName("плд");
        student.setAge(23);
        assertThat(this.restTemplate.postForObject("http://localhost:"+port+"/student",student,String.class))
                .isNotNull();
    }
    @Test
    public void testPutStudent()throws Exception{
        Student student = new Student();
        HttpEntity<Student> htStudent = new HttpEntity<>(student);
        student.setId(1);
        student.setAge(22);
        student.setName("валдв");
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:"+port+"/student"+ student.getId(), HttpMethod.PUT,htStudent,Student.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteStudent() throws Exception{
        Student student = new Student();
        student.setId(1);
        student.setName("плд");
        student.setAge(23);
        studentController.createStudent(student);
        studentController.deleteStudent(student.getId());
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/student",String.class))
                .isNotNull();
    }

}
