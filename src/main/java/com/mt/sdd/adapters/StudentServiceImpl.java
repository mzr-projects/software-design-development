package com.mt.sdd.adapters;

import com.mt.sdd.domains.student.Student;
import com.mt.sdd.domains.student.StudentDto;
import com.mt.sdd.ports.IDDService;
import com.mt.sdd.ports.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements IDDService<StudentDto, Student> {

    private final StudentRepository studentRepository;

    @Override
    public Student create(StudentDto s) {
        Student student = new Student();
        student.setName(s.getName());
        Student mentorStudent = new Student();
        mentorStudent.setName(s.getMentorName());
        student.setMentorStudent(mentorStudent);
        return studentRepository.save(student);
    }

    @Override
    public void delete(StudentDto studentDto) {

    }

    public void deleteByStudentObject(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void edit(StudentDto studentDto) {

    }

    public Student findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    @Override
    public List<StudentDto> findAll() {
        return null;
    }
}
