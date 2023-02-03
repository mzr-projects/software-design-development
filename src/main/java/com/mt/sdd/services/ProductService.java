package com.mt.sdd.services;

import com.mt.sdd.dtos.ProductDto;
import com.mt.sdd.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    void create(ProductDto productDto);
}
