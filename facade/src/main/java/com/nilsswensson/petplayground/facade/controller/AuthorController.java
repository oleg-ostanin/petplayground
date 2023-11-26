package com.nilsswensson.petplayground.facade.controller;

import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.facade.service.AuthorService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rest")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Timed
    @GetMapping("/author")
    public ResponseEntity<Author> getAuthor() {
        return ResponseEntity.ok(authorService.getAuthorByFirstnameAndLastname("Sergey", "Gorokhov"));
    }

    @Timed
    @PostMapping("/add-author")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void addAuthor(@RequestBody final Author author) {
        authorService.addAuthor(author);
    }
}
