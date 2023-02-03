package com.mt.sdd;

import com.mt.sdd.dtos.ProductDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public enum ProductInMemoryDB {
    INSTANCE;

    private static List<ProductDto> list = new ArrayList<>();

    private static Integer lastId = 0;

    public Integer getId() {
        return ++lastId;
    }

    public void add(ProductDto productDto) {
        productDto.setId(getId());
        list.add(productDto);
    }

    public List<ProductDto> findAll() {
        return list;
    }

    public Optional<ProductDto> findById(Integer id) {
        return list.stream().filter(data -> data.getId().equals(id)).findFirst();
    }

    public void remove(Integer id) {
        ProductDto productDto = null;
        for (ProductDto product : list) {
            if (product.getId().equals(id)) {
                productDto = product;
            }
        }

        if (productDto != null) {
            log.info("Product with id {} removed.", id);
            list.remove(productDto);
        } else log.info("Product with id {} does not exist", id);
    }
}
