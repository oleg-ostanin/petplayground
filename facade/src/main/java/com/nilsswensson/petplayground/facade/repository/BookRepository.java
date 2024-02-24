package com.nilsswensson.petplayground.facade.repository;

import com.nilsswensson.petplayground.facade.entity.BookEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @NotNull
    @Override
    List<BookEntity> findAll();

    Optional<BookEntity> findByTitle(String title);
}
