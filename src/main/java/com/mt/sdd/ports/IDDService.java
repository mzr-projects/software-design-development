package com.mt.sdd.ports;

import java.util.List;

public interface IDDService<T,U> {

    U create(T s);

    void delete(T t);

    void edit(T t);

    List<T> findAll();
}
