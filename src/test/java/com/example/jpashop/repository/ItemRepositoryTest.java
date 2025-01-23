package com.example.jpashop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.jpashop.domain.Book;
import com.example.jpashop.domain.Item;
import com.example.jpashop.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void test() {
        assertNotNull(itemRepository);
    }

    @Test
    @Rollback(false)
    public void testSave() {

        // given
        Book book = new Book();
        book.setAuthor("author1");
        book.setIsbn("isbn1");
        book.setName("item1");
        book.setPrice(10000);
        book.setStockQuantity(100);

        Movie movie = new Movie();
        movie.setDirector("봉준호");
        movie.setActor("송강호");
        movie.setName("기생충");
        movie.setPrice(10000);
        movie.setStockQuantity(50);

        // when
        itemRepository.save(book);

        itemRepository.save(movie);

        // then
        assertNotNull(movie.getId());
        assertNotNull(book.getId());
    }

}
