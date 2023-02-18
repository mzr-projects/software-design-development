package com.mt.sdd.adapters;

import com.mt.sdd.domains.customer.Cart;
import com.mt.sdd.domains.customer.Customer;
import com.mt.sdd.domains.customer.CustomerDto;
import com.mt.sdd.domains.customer.mappers.CustomerMapper;
import com.mt.sdd.ports.CartRepository;
import com.mt.sdd.ports.CustomerRepository;
import com.mt.sdd.ports.IDDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements IDDService<CustomerDto, Customer> {

    private final CartRepository cartRepository;

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapDtoToEntity(customerDto);
        log.info("Customer with name : {} and Cart with amount of : {} are created.",
                customer.getName(), customerDto.getAmount());
        return customerRepository.save(customer);
    }

    @Override
    public void delete(CustomerDto customerDto) {
        Integer customerId = customerRepository.deleteCustomers(customerDto.getId());
        log.info("Customer with id : {} deleted.", customerId);
    }

    public void deleteById(Integer id){
        Integer customerId = customerRepository.deleteCustomers(id);
        log.info("Customer with id : {} deleted.", customerId);
    }

    public void deleteCartById(Integer id){
        Integer customerId = cartRepository.deleteCart(id);
        log.info("Cart with id : {} deleted.", customerId);
    }

    public void deleteByObject(Customer customer){
        customerRepository.delete(customer);
        log.info("Customer with id : {} deleted.", customer.getId());

    }

    @Override
    public void edit(CustomerDto customerDto) {

    }

    @Override
    public List<CustomerDto> findAll() {
        return null;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    public List<Cart> findAllCarts(){
        return cartRepository.findAllCarts();
    }
}
