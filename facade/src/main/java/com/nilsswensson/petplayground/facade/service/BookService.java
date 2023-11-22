package com.nilsswensson.petplayground.facade.service;

import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.facade.entity.BookEntity;
import com.nilsswensson.petplayground.facade.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final RedissonClient redissonClient;



    public Book getByTitle(final String title) {
        RDeque<String> deque = redissonClient.getDeque("test_deque");
        deque.addFirst("A", "B", "C");
        for (String letter: deque) {
            log.info("letter: {}", letter);
        }


        final Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);

        if (bookEntity.isPresent()) {
            return Book.builder().title(bookEntity.get().getTitle()).build();
        }

        throw new RuntimeException("Failed to find book by title " + title);
    }

    @Transactional
    public void addBook(final Book book) {
        final Optional<BookEntity> check = bookRepository.findByTitle(book.getTitle());

        if(check.isPresent()) {
            return;
        }

        final BookEntity bookEntity = BookEntity.builder().title(book.getTitle()).build();
        bookRepository.save(bookEntity);
    }
}
