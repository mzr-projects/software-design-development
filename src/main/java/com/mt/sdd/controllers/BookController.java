package com.mt.sdd.controllers;

import com.mt.sdd.adapters.BookInMemoryDB;
import com.mt.sdd.adapters.BookServiceImpl;
import com.mt.sdd.domains.book.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/one-to-one-unidirectional")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        bookService.create(bookDto);
        return new ResponseEntity<>("DONE", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BookDto>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(BookInMemoryDB.INSTANCE.findById(bookId), HttpStatus.OK);
    }
}
