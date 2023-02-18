package com.mt.sdd.adapters;

import com.mt.sdd.domains.student.StudentDto;

import java.util.ArrayList;
import java.util.List;

public enum StudentInMemoryDB {
    INSTANCE;

    private static Integer lastId = 0;

    private static List<StudentDto> list = new ArrayList<>();

    public Integer getId() {
        return ++lastId;
    }

    public void add(StudentDto studentDto) {
        studentDto.setId(getId());
        list.add(studentDto);
    }

    public List<StudentDto> findAll() {
        return list;
    }

    public StudentDto findByName(String name) {
        return list.stream().filter((student) -> student.getName().equals(name)).findFirst().orElse(null);
    }
}
