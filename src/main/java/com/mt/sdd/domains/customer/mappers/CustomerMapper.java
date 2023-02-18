package com.mt.sdd.domains.customer.mappers;

import com.mt.sdd.domains.customer.Cart;
import com.mt.sdd.domains.customer.Customer;
import com.mt.sdd.domains.customer.CustomerDto;

public class CustomerMapper {

    private CustomerMapper() {

    }

    public static Customer mapDtoToEntity(CustomerDto customerDto) {
        Cart cart = new Cart();
        cart.setAmount(customerDto.getAmount());

        Customer customer = new Customer();
        if (null != customerDto.getName()) customer.setName(customerDto.getName());
        customer.setCart(cart);

        return customer;
    }

    public static CustomerDto mapEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        if (null != customer.getId()) customerDto.setId(customer.getId());
        if (null != customer.getName()) customerDto.setName(customer.getName());

        return customerDto;
    }
}
