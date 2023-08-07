package com.example.Dz22.service;

import com.example.Dz22.model.Avatar;
import com.example.Dz22.model.Faculty;
import com.example.Dz22.model.Student;
import com.example.Dz22.repository.AvatarRepository;
import com.example.Dz22.repository.StudentRepository;
import liquibase.pro.packaged.O;
import liquibase.sdk.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentServiceImpl implements StudentService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    @Override
    public Student addStudent(Student student) {
        logger.info("Request add student");
        studentRepository.save(student);
        logger.info("Student is created - " + student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Request find student by id");
        return studentRepository.findById(id).get();
    }

    public Faculty findFacultyByStudentId(Long studentId) {
        logger.info("Request find faculty student by ID ");
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            logger.info("Student by ID  - " + studentOptional.get());
            return student.getFaculty();
        }
        return null;
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Request edit student");
        studentRepository.save(student);
        logger.info("Student edition" + student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Request delete student");
        studentRepository.deleteById(id);
        logger.info("Student with id " + id + "deleted");
    }

    public Collection<Student> findByAge(int min, int max) {
        logger.info("Request students avg age");
        logger.info("Students > " + min + "< " + max + "this is " + studentRepository.findByAgeBetween(min, max));
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Avatar findAvatar(long studentId) {
        logger.info("Request find Avatar students");
        logger.info("Students avatar is - " + avatarRepository.findByStudentId(studentId).orElseThrow());
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = findStudent(studentId);


        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);
    }

    @Override
    public Integer countAllStudents() {
        logger.info("Request count all students");
        logger.info("All students in swagger ");
        return studentRepository.countStudentsAll();
    }

    @Override
    public Integer AvgAgeStudent() {
        logger.info("Request avg age stundets");
        logger.info("student found");
        return studentRepository.avgAgeStudent();
    }

    @Override
    public List<Student> fiveLastStudents() {
        logger.info("Request five last students ");
        return studentRepository.fiveLastStudents();
    }

    @Override
    public List<Avatar> allAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Request all avatars students");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Student> studentsWithA() {
        List<Student> students = studentRepository.findAll().stream().filter(student -> student.getName().startsWith("A")).collect(Collectors.toList());
        return students;
    }

    @Override
    public Double studentAvgAge() {
        DoubleSummaryStatistics avg = studentRepository.findAll().stream().mapToDouble(Student::getAge).summaryStatistics();
        return avg.getAverage();
    }

    private String getExtension(String fileName) {
        logger.info("Request get extension");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void studentThread() {
        System.out.println(studentRepository.findAll().get(0).getName());
        System.out.println(studentRepository.findAll().get(1).getName());
        Thread thread1 = new Thread(()->{
            System.out.println(studentRepository.findAll().get(2).getName());
            System.out.println(studentRepository.findAll().get(3).getName());
        });thread1.start();
        Thread thread2 = new Thread(()->{
            System.out.println(studentRepository.findAll().get(4).getName());
            System.out.println(studentRepository.findAll().get(5).getName());
        });thread2.start();
        }

    @Override
    public synchronized void studentsThread2(){
        print(studentRepository.findAll().get(0).getName());
        print(studentRepository.findAll().get(1).getName());
        Thread thread1 = new Thread(()->{
            print(studentRepository.findAll().get(2).getName());
            print(studentRepository.findAll().get(3).getName());
        });thread1.start();
        Thread thread2 = new Thread(()->{
            print(studentRepository.findAll().get(4).getName());
            print(studentRepository.findAll().get(5).getName());
        });thread2.start();

        }

        public synchronized void print(String o){
            System.out.println(o);
        }

}






