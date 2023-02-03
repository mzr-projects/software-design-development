package com.mt.sdd;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;
}
