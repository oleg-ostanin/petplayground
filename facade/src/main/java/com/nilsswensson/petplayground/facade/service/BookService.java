package com.nilsswensson.petplayground.facade.service;

import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.facade.entity.BookEntity;
import com.nilsswensson.petplayground.facade.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getByTitle(final String title) {
        final Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);

        if (bookEntity.isPresent()) {
            return Book.builder().title(bookEntity.get().getTitle()).build();
        }

        throw new RuntimeException("Failed to find book by title " + title);
    }

    public void addBook(final Book book) {
        final BookEntity bookEntity = BookEntity.builder().title(book.getTitle()).build();
        bookRepository.save(bookEntity);
    }
}
