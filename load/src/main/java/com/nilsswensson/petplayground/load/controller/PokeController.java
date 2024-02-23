package com.nilsswensson.petplayground.load.controller;

import com.nilsswensson.petplayground.common.model.book.Author;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poke")
@RequiredArgsConstructor
public class PokeController {
    @Timed
    @GetMapping()
    public ResponseEntity<Author> poke() {
        return ResponseEntity.ok(new Author("Poke1", "Poke1"));
    }
}
