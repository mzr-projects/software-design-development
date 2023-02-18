package com.mt.sdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mt.sdd.domains.book.BookDto;
import com.mt.sdd.domains.book.Book;
import com.mt.sdd.domains.book.Shipping;
import com.mt.sdd.ports.BookRepository;
import com.mt.sdd.ports.ShippingRepository;
import com.mt.sdd.ports.ProductService;
import com.mt.sdd.adapters.BookServiceImpl;
import com.mt.sdd.adapters.ShippingServiceImpl;
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
class BookTests {

    private final Gson gson = new GsonBuilder().create();

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ShippingServiceImpl shippingServiceImpl;

    @Autowired
    private BookServiceImpl bookServiceImpl;

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
    void testBookPersistence() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Shipping shipping1 = new Shipping();
        shipping1.setCity("New york");

        Book Book1 = new Book();
        Book1.setName("Brilliant");
        Book1.setShipping(shipping1);

        Shipping shipping2 = new Shipping();
        shipping2.setCity("San Diego");

        Book Book2 = new Book();
        Book2.setName("Vague");
        Book2.setShipping(shipping2);

        entityManager.persist(Book1);
        entityManager.persist(Book2);

        entityManager.joinTransaction();
        entityManager.flush();

        Book1.setName("Forbes");
        entityManager.merge(Book1);

        entityManager.joinTransaction();
        entityManager.flush();

        List<Book> BookList1 = entityManager.createQuery("from Book").getResultList();
        List<Shipping> shippingList1 = entityManager.createQuery("from Shipping").getResultList();
        Assertions.assertEquals(2L, BookList1.size());
        Assertions.assertEquals(2L, shippingList1.size());

        entityManager.remove(Book1);
        entityManager.joinTransaction();
        entityManager.flush();

        List<Book> BookList2 = entityManager.createQuery("from Book").getResultList();
        List<Shipping> shippingList2 = entityManager.createQuery("from Shipping").getResultList();
        Assertions.assertEquals(1L, BookList2.size());
        Assertions.assertEquals(1L, shippingList2.size());
    }

    @Test
    void testRepository() {
        Book Book1 = new Book();
        Book1.setName("NP");

        Book Book2 = new Book();
        Book2.setName("NPN");

        bookRepository.save(Book1);
        bookRepository.save(Book2);

        List<Book> productList = bookRepository.findAll();
        Assertions.assertEquals(2L, productList.size());
    }

    @Test
    void testFindRow() {
        Book book1 = new Book();
        book1.setName("NP");

        Book book2 = new Book();
        book2.setName("NPN");

        bookRepository.save(book1);
        bookRepository.save(book2);

        Optional<Book> product = bookRepository.findById(book1.getId());
        Assertions.assertNotNull(product);
        Assertions.assertEquals("NP", product.get().getName());
    }

    /*
     * Here if we remove book the shipping will be deleted as well because it's in a
     * CascadeAll mode.
     * */
    @Test
    void testRemoveShipping() {

        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setName("Harry Potter");
        bookDto.setCity("New York");

        bookServiceImpl.create(bookDto);
        bookServiceImpl.delete(bookDto);
        //shippingServiceImpl.delete(book.getShipping());

        Assertions.assertTrue(bookRepository.findAll().isEmpty());
        Assertions.assertTrue(shippingRepository.findAll().isEmpty());

    }

    @Test
    void testEditShipping() {

        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setName("Lord of the Rings");
        bookDto.setCity("LA");

        bookServiceImpl.create(bookDto);
        shippingServiceImpl.edit(bookDto);

        Assertions.assertTrue(bookRepository.findAll().isEmpty());
        Assertions.assertTrue(shippingRepository.findAll().isEmpty());

    }

    @Test
    void testCreateWithMock() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setName("ABC");
        bookDto.setCity("Memphis");

        String json = gson.toJson(bookDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/one-to-one-unidirectional/create");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(json);

        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
