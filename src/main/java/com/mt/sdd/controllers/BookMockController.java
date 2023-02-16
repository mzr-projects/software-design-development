package com.mt.sdd.controllers;

import com.mt.sdd.domains.book.BookDto;
import com.mt.sdd.adapters.BookInMemoryDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/one-to-one-unidirectional/mock")
public class BookMockController {

    @PostMapping(value = "/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        BookInMemoryDB.INSTANCE.add(bookDto);
        return new ResponseEntity<>("DONE", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BookDto>> findAll() {
        return new ResponseEntity<>(BookInMemoryDB.INSTANCE.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(BookInMemoryDB.INSTANCE.findById(bookId), HttpStatus.OK);
    }
}
