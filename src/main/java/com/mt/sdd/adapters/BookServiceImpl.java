package com.mt.sdd.adapters;

import com.mt.sdd.domains.book.Book;
import com.mt.sdd.domains.book.BookDto;
import com.mt.sdd.domains.book.mappers.BookMapper;
import com.mt.sdd.ports.BookRepository;
import com.mt.sdd.ports.IDDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements IDDService<BookDto, Book> {

    private final ShippingServiceImpl shippingServiceImpl;

    private final BookRepository bookRepository;

    @Override
    public Book create(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setShipping(shippingServiceImpl.create(bookDto));
        log.info("Book with name : {} and id : {} saved.",bookDto.getName(),bookDto.getId());
        return bookRepository.save(book);
    }

    @Override
    public void delete(BookDto book) {
        log.info("Book with id : {}, name : {} deleted.", book.getId(), book.getName());
        bookRepository.deleteById(book.getId());
    }

    public void deleteByObject(Book book){
        bookRepository.delete(book);
    }

    @Override
    public void edit(BookDto bookDto) {
        bookDto.setName("LA");
        Book book = BookMapper.mapDtoToEntity(bookDto);
        log.info("Book with id : {} edited.", bookDto.getId());
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();

        books.forEach((book) -> {
            BookDto bookDto = new BookDto();
            bookDto.setCity(book.getShipping().getCity());
            bookDto.setId(book.getId());
            book.setName(book.getName());
            bookDtoList.add(bookDto);
        });

        return bookDtoList;
    }
}
