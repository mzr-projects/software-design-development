package com.mt.sdd.adapters;

import com.mt.sdd.domains.customer.CustomerDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public enum CustomerInMemoryDB {
    INSTANCE;

    private static Integer lastId = 0;
    private static List<CustomerDto> customerList = new ArrayList<>();

    public Integer getLastId() {
        return ++lastId;
    }

    public void add(CustomerDto customerDto) {
        customerDto.setId(getLastId());
        customerList.add(customerDto);
        log.info("Customer with id : {} added into the mock list.", customerDto.getId());
    }

    public List<CustomerDto> findAll() {
        return customerList;
    }

    public CustomerDto findById(Integer id) {
        Optional<CustomerDto> customerDto = customerList.stream().filter((customer) -> customer.getId().equals(id))
                .findFirst();
        log.info("Customer by id : {} is : {}", id, customerDto.orElse(null));
        return customerDto.orElse(null);
    }
}
