package com.mt.sdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mt.sdd.dtos.ProductDto;
import com.mt.sdd.entities.Product;
import com.mt.sdd.repositories.ProductRepository;
import com.mt.sdd.services.ProductService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@WebAppConfiguration
class SoftwareDesignDevelopmentApplicationTests {

    private final Gson gson = new GsonBuilder().create();

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
     * This test uses EntityManagerFactory to save and retrieve data from database
     * */
    @Test
    void testPersistence() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Product p1 = new Product();
        p1.setName("Soap");

        Product p2 = new Product();
        p2.setName("Phone");

        entityManager.persist(p1);
        entityManager.persist(p2);

        entityManager.joinTransaction();
        entityManager.flush();

        p1.setName("Cable");
        entityManager.merge(p1);

        entityManager.joinTransaction();
        entityManager.flush();

        List<Product> productList1 = entityManager.createQuery("from Product").getResultList();
        Assertions.assertEquals(2L, productList1.size());

        entityManager.remove(p1);

        entityManager.joinTransaction();
        entityManager.flush();

        List<Product> productList2 = entityManager.createQuery("from Product").getResultList();
        Assertions.assertEquals(1L, productList2.size());
    }

    @Test
    void testRepository() {
        Product p1 = new Product();
        p1.setName("NP");

        Product p2 = new Product();
        p2.setName("NPN");

        productRepository.save(p1);
        productRepository.save(p2);

        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(2L, productList.size());
    }

    @Test
    void testFindRow() {
        Product p1 = new Product();
        p1.setName("NP");

        Product p2 = new Product();
        p2.setName("NPN");

        productRepository.save(p1);
        productRepository.save(p2);

        Optional<Product> product = productRepository.findById(p1.getId());
        Assertions.assertNotNull(product);
        Assertions.assertEquals("NP", product.get().getName());
    }

    @Test
    void testFindAllByService() {
        int size;
        if (productService.findAllProducts() != null)
            size = 0;
        else
            size = productService.findAllProducts().size();

        Assertions.assertEquals(0, size);
    }

    @Test
    void testCreateByService() {
        ProductDto productDto = new ProductDto();
        productDto.setName("NEW P");
        productService.create(productDto);
        Assertions.assertEquals(1L, productService.findAllProducts().size());
    }

    @Test
    void testCreateWithMock() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("ABC");

        String json = gson.toJson(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/standalone/mock/create");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(json);

        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
