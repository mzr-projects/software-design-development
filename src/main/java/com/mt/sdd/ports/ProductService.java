package com.mt.sdd.ports;

import com.mt.sdd.domains.product.ProductDto;
import com.mt.sdd.domains.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    void create(ProductDto productDto);
}
