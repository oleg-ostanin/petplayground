package com.nilsswensson.petplayground.facade.security.demo;

import com.nilsswensson.petplayground.common.model.Book;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/book")
  public ResponseEntity<Book> book() {
    return ResponseEntity.ok(Book.builder().title("name").build());
  }

}
