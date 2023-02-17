package com.mt.sdd.adapters;

import com.mt.sdd.domains.book.Book;
import com.mt.sdd.domains.book.BookDto;
import com.mt.sdd.domains.book.Shipping;
import com.mt.sdd.domains.book.mappers.BookMapper;
import com.mt.sdd.ports.IDDService;
import com.mt.sdd.ports.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ShippingServiceImpl implements IDDService<BookDto, Shipping> {

    private final ShippingRepository shippingRepository;

    @Override
    public Shipping create(BookDto bookDto) {
        Shipping shipping = new Shipping();
        shipping.setCity(bookDto.getCity());
        log.info("Shipping with name : {} saved.", bookDto.getCity());
        return shippingRepository.save(shipping);
    }

    @Override
    public void delete(BookDto bookDto) {
        log.info("Shipping by id {} and city {} deleted.", bookDto.getId(), bookDto.getCity());
        shippingRepository.deleteById(bookDto.getId());
    }

    @Override
    public void edit(BookDto bookDto) {
        bookDto.setCity("New Hampshire");
        Book book = BookMapper.mapDtoToEntity(bookDto);
        log.info("Shipping with id : {} and city {} edited.", bookDto.getId(), bookDto.getCity());
        shippingRepository.save(book.getShipping());
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }
}
