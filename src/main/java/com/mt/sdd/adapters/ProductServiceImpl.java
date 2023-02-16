package com.mt.sdd.adapters;

import com.mt.sdd.domains.product.ProductDto;
import com.mt.sdd.domains.product.Product;
import com.mt.sdd.domains.product.mappers.ProductMapper;
import com.mt.sdd.ports.ProductRepository;
import com.mt.sdd.ports.ProductService;
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
