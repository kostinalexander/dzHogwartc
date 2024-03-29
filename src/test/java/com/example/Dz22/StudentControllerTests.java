package com.example.Dz22;

import com.example.Dz22.controller.FacultyController;
import com.example.Dz22.controller.StudentController;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.AvatarRepository;
import com.example.Dz22.repository.FacultyRepository;
import com.example.Dz22.repository.StudentRepository;
import com.example.Dz22.service.FacultyService;
import com.example.Dz22.service.FacultyServiceImpl;
import com.example.Dz22.service.StudentService;
import com.example.Dz22.service.StudentServiceImpl;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({StudentController.class,FacultyController.class})
@ComponentScan("com.example.Dz22")
public class StudentControllerTests {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
   private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private FacultyRepository facultyRepository;


    @SpyBean
    private StudentService studentServiceImpl;
    @SpyBean
    private FacultyService facultyService;

   @InjectMocks
   private StudentController studentController;



    @Test
    public void saveStudentTest() throws Exception{
        final String name = "sss";
        final int age = 13;
        final int id = 1;

        JSONObject studentObject =new JSONObject();
        studentObject.put("name",name );
        studentObject.put("age",age);
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));




        mockMvc.perform(MockMvcRequestBuilders
                .post("/student") //POST
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders //GET
                .get("/student/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
        mockMvc.perform(MockMvcRequestBuilders  //PUT
                .put("/student/1")
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders  // DELETE
                        .delete("/student/1")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }





    }


