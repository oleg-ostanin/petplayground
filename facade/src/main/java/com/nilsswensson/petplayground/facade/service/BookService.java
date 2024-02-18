package com.nilsswensson.petplayground.facade.service;

import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.facade.entity.AuthorEntity;
import com.nilsswensson.petplayground.facade.entity.BookEntity;
import com.nilsswensson.petplayground.facade.repository.AuthorRepository;
import com.nilsswensson.petplayground.facade.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final RedissonClient redissonClient;



    public Book getByTitle(final String title) {
        RDeque<String> deque = redissonClient.getDeque("test_deque");

        deque.addFirst("A", "B", "C");
        for (String letter: deque) {
            //log.info("letter: {}", letter);
        }


        final Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);

        if (bookEntity.isPresent()) {
            return Book.builder().title(bookEntity.get().getTitle()).build();
        }

        throw new RuntimeException("Failed to find book by title " + title);
    }

    public void addBook(final Book book) {
        final BookEntity bookEntity = BookEntity.builder().title(book.getTitle()).build();
        Optional<BookEntity> existingBooks = bookRepository.findByTitle(book.getTitle());
        if (existingBooks.isPresent()) {
            throw new RuntimeException("A book with title " + book.getTitle() + " already exists.");
        } else {
            bookRepository.save(bookEntity);
        }
    }

    @Transactional
    public void attachAuthor(Long bookId, Author author) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
        Optional<AuthorEntity> authorEntity = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        if(bookEntity.isPresent() && authorEntity.isPresent()) {
            final BookEntity book = bookEntity.get();
            book.setAuthors(new HashSet<>(Set.of(authorEntity.get())));
            bookRepository.save(book);
            //authorEntity.get().setBooks(new HashSet<>(Set.of(bookEntity.get())));
            return;
        }

        if(bookEntity.isEmpty()) {
            throw new RuntimeException("Failed attach author to book because book with id " + bookId+" does`t exist");
        }

        if(authorEntity.isEmpty()) {
            throw new RuntimeException("Failed attach author to book because author with name " + author.getFirstName()+" "+author.getLastName()+" does`t exist");
        }
    }
}
