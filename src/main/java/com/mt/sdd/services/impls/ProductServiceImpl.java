package com.mt.sdd.services.impls;

import com.mt.sdd.dtos.ProductDto;
import com.mt.sdd.entities.Product;
import com.mt.sdd.mappers.ProductMapper;
import com.mt.sdd.repositories.ProductRepository;
import com.mt.sdd.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void create(ProductDto productDto) {
        Product product = productMapper.mapDtoToEntity(productDto);
        productRepository.save(product);
    }
}
