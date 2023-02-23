package com.mt.sdd.domains.person;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonDto {
    private Integer id;

    private String name;

    private Set<String> numbers;
}
