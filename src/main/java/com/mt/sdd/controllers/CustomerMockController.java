package com.mt.sdd.controllers;

import com.mt.sdd.adapters.CustomerInMemoryDB;
import com.mt.sdd.domains.customer.CustomerDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/one-to-one-bidirectional/mock")
public class CustomerMockController {

    @PostMapping("/create")
    public void create(@RequestBody CustomerDto customerDto){
        CustomerInMemoryDB.INSTANCE.add(customerDto);
    }

    @GetMapping("/findAll")
    public List<CustomerDto> findAll(){
        return CustomerInMemoryDB.INSTANCE.findAll();
    }

    @GetMapping("/findById/{id}")
    public CustomerDto findById(@PathVariable int id){
        return CustomerInMemoryDB.INSTANCE.findById(id);
    }
}
