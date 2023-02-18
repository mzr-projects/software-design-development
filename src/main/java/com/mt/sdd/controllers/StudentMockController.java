package com.mt.sdd.controllers;

import com.mt.sdd.adapters.StudentInMemoryDB;
import com.mt.sdd.domains.student.StudentDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/one-to-one-self-reference/mock")
public class StudentMockController {

    @PostMapping("/create")
    public void create(@RequestBody StudentDto studentDto) {
        StudentInMemoryDB.INSTANCE.add(studentDto);
    }

    @GetMapping("/findAll")
    public List<StudentDto> findAll() {
        return StudentInMemoryDB.INSTANCE.findAll();
    }

    @GetMapping("/findByName/{name}")
    public StudentDto findByName(@PathVariable String name) {
        return StudentInMemoryDB.INSTANCE.findByName(name);
    }
}
