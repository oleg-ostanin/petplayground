package com.nilsswensson.petplayground.facade.repository;

import com.nilsswensson.petplayground.facade.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitle(String title);
}
