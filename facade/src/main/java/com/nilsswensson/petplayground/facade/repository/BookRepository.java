package com.nilsswensson.petplayground.facade.repository;

import com.nilsswensson.petplayground.facade.entity.BookEntity;
import com.nilsswensson.petplayground.facade.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitle(String title);
}
