package com.mt.sdd.ports;

import com.mt.sdd.domains.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
