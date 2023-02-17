package com.mt.sdd.adapters;

import com.mt.sdd.domains.book.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum BookInMemoryDB {
    INSTANCE;

    private static List<BookDto> list = new ArrayList<>();

    private static Integer lastId = 0;

    public static Integer getLastId() {
        return ++lastId;
    }

    public void add(BookDto bookDto) {
        bookDto.setId(getLastId());
        list.add(bookDto);
    }

    public List<BookDto> findAll() {
        return list;
    }

    public BookDto findById(Integer id) {
        Optional<BookDto> bookDto = list.stream().filter((book) -> book.getId().equals(id)).findFirst();
        return bookDto.orElse(null);
    }
}
