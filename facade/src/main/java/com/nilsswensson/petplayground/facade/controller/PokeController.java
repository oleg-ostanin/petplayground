package com.nilsswensson.petplayground.facade.controller;

import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.facade.service.AuthorService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poke")
@RequiredArgsConstructor
public class PokeController {

    private final AuthorService authorService;

    @Timed
    @GetMapping()
    public ResponseEntity<Author> poke() {
        return ResponseEntity.ok(new Author("Poke", "Poke"));
    }
}
