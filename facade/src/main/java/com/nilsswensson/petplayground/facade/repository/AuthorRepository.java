package com.nilsswensson.petplayground.facade.repository;

import com.nilsswensson.petplayground.facade.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByFirstNameAndLastName(String firstname, String lastname);
}
