package com.nilsswensson.petplayground.facade.controller;

import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.facade.service.BookService;
import com.nilsswensson.petplayground.facade.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poke")
@RequiredArgsConstructor
public class PokeController {

    private final BookService bookService;
    private final UserService userService;
    @Timed
    @GetMapping()
    public ResponseEntity<Author> poke() {
        return ResponseEntity.ok(new Author("Poke1", "Poke1"));
    }

    @Timed
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Timed
    @PostMapping("/add-book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok(bookService.findAll());
    }
}
