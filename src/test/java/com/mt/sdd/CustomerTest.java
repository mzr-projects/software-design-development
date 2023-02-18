package com.mt.sdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mt.sdd.adapters.CustomerServiceImpl;
import com.mt.sdd.domains.customer.Cart;
import com.mt.sdd.domains.customer.Customer;
import com.mt.sdd.domains.customer.CustomerDto;
import com.mt.sdd.ports.CartRepository;
import com.mt.sdd.ports.CustomerRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class CustomerTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerTest.class);

    private final Gson gson = new GsonBuilder().create();

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private static CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("James");
        customerDto.setAmount(123);
        return customerDto;
    }

    @Test
    void testCreation() {

        CustomerDto customerDto = createCustomerDto();
        Customer customer = customerService.create(customerDto);
        Optional<Customer> customerTemp = customerRepository.findById(customer.getId());
        List<Cart> cartTemp = customerService.findAllCarts();

        Assertions.assertEquals(customerTemp.get().getName(), customer.getName());
        Assertions.assertEquals(cartTemp.size(), 1);
        Assertions.assertEquals(cartTemp.get(0).getAmount(), customerDto.getAmount());
    }

    @Test
    void testRemovingEitherSides() {

        CustomerDto customerDto = createCustomerDto();

        Customer customer = customerService.create(customerDto);

        Optional<Customer> customerTemp = customerRepository.findById(customer.getId());
        log.info("Customer is : {}", customerTemp);
        log.info("Cart associated with customer fetched from database : {}", customer.getCart());

        //customerService.deleteById(customer.getId());
        //customerService.deleteCartById(customer.getCart().getId());
        /*
         * The cascade deletion works by passing the whole object to the delete method.
         * Not just finding it with ID
         * */
        customerService.deleteByObject(customerTemp.get());

        List<Cart> cartTemp = customerService.findAllCarts();
        List<Customer> customers = customerService.findAllCustomers();

        cartTemp.forEach((cart) -> log.info(cart.toString()));
        Cart cart = cartTemp.stream().findFirst().orElse(null);

        Assertions.assertEquals(customerTemp.get().getName(), customer.getName());
        Assertions.assertNull(cart);
        Assertions.assertEquals(customers.size(), 0);
        Assertions.assertEquals(cartTemp.size(), 0);
    }
}
