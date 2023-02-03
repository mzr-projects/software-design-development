package com.mt.sdd.mappers;

import com.mt.sdd.dtos.ProductDto;
import com.mt.sdd.entities.Product;
import org.springframework.stereotype.Component;

import java.time.Period;

@Component
public class ProductMapper {

    public Product mapDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) product.setId(product.getId());
        if (productDto.getName() != null) product.setName(product.getName());
        return product;
    }

    public ProductDto mapEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (product.getId() != null) productDto.setId(product.getId());
        if (product.getName() != null) productDto.setName(product.getName());
        return productDto;
    }
}
