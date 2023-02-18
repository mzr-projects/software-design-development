package com.mt.sdd.ports;

import com.mt.sdd.domains.student.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findStudentByName(String name);
}
