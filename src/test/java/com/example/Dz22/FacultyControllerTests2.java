package com.example.Dz22;

import com.example.Dz22.controller.FacultyController;
import com.example.Dz22.controller.StudentController;
import com.example.Dz22.model.Faculty;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTests2 {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testLoads(){
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFaculty()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/faculty",String.class))
                .isNotNull();
    }

    @Test
    public void testCreateFacultyAndDelete() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("плд");
        faculty.setColor("red");
        assertThat(this.restTemplate.postForObject("http://localhost:"+port+"/student",faculty,String.class))
                .isNotNull();
    }
    @Test
    public void testPutFaculty()throws Exception{
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> htFaculty = new HttpEntity<>(faculty);
        faculty.setId(1);
        faculty.setColor("лавд");
        faculty.setName("валдв");
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:"+port+"/faculty"+ faculty.getId(), HttpMethod.PUT,htFaculty,Student.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteStudent() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("плд");
        faculty.setColor("klfk");
        facultyController.createFaculty(faculty);
        facultyController.deleteFaculty(faculty.getId());
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/faculty",String.class))
                .isNotNull();
    }
}
