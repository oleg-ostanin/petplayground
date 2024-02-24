package com.nilsswensson.petplayground.facade.repository;

import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.facade.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book fromEntity(BookEntity entity) {
        return Book.builder()
                .title(entity.getTitle())
                .build();
    }

    public List<Book> fromEntities(List<BookEntity> entities) {
        return entities.stream().map(this::fromEntity).toList();
    }

    public BookEntity fromPojo(Book pojo) {
        return BookEntity.builder()
                .title(pojo.getTitle())
                .build();
    }

    public List<BookEntity> fromPojos(List<Book> pojos) {
        return pojos.stream().map(this::fromPojo).toList();
    }
}
