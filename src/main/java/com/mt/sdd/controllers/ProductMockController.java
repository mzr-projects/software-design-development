package com.mt.sdd.controllers;

import com.mt.sdd.adapters.ProductInMemoryDB;
import com.mt.sdd.domains.product.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/standalone/mock")
@Slf4j
public class ProductMockController {

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProductDto productDto) {
        log.info("We are in controller CREATE");
        ProductInMemoryDB.INSTANCE.add(productDto);
        return new ResponseEntity<>("DONE", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductDto>> findAll() {
        log.info("We are in controller FIND_ALL");
        return new ResponseEntity<>(ProductInMemoryDB.INSTANCE.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable("productId") Integer id) {
        log.info("We are in controller FIND_BY_ID");
        if (ProductInMemoryDB.INSTANCE.findById(id).isPresent())
            return new ResponseEntity<>(ProductInMemoryDB.INSTANCE.findById(id).get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteById(@PathVariable("productId") Integer id) {
        log.info("We are in controller DELETE_BY_ID");
        ProductInMemoryDB.INSTANCE.remove(id);
    }
}
