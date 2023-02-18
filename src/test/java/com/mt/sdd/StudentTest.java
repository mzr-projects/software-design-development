package com.mt.sdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mt.sdd.adapters.StudentServiceImpl;
import com.mt.sdd.domains.student.Student;
import com.mt.sdd.domains.student.StudentDto;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class StudentTest {

    private final Gson gson = new GsonBuilder().create();

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private static StudentDto createStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Joe");
        studentDto.setMentorName("Mike");
        return studentDto;
    }

    @Test
    void testStudentCreation() {
        StudentDto studentDto = createStudent();
        Student student = studentService.create(studentDto);
        Student storedStudent = studentService.findStudentByName(studentDto.getName());

        Assertions.assertEquals(student.getName(), studentDto.getName());
        Assertions.assertEquals(storedStudent.getName(), studentDto.getName());
        Assertions.assertEquals(storedStudent.getMentorStudent().getName(),studentDto.getMentorName());
    }

    @Test
    void testStudentDeletion() {
        StudentDto studentDto = createStudent();
        Student student = studentService.create(studentDto);
        Student mentorStudent = student.getMentorStudent();

        studentService.deleteByStudentObject(student);

        Student storedStudent = studentService.findStudentByName(studentDto.getName());
        Student storedMentorStudent = studentService.findStudentByName(mentorStudent.getName());

        Assertions.assertNull(storedStudent);
        Assertions.assertNull(storedMentorStudent);
    }
}
