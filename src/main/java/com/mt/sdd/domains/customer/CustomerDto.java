package com.mt.sdd.domains.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {
    private Integer id;

    private String name;

    private double amount;
}
