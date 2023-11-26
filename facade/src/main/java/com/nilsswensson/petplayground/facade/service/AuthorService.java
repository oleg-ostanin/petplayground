package com.nilsswensson.petplayground.facade.service;

import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.facade.entity.AuthorEntity;
import com.nilsswensson.petplayground.facade.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@AllArgsConstructor
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author getAuthorByFirstnameAndLastname(final String firstname, final String lastname) {
        final Optional<AuthorEntity> authorEntity = authorRepository.findByFirstNameAndLastName(firstname,lastname);

        if (authorEntity.isPresent()) {
            return Author.builder()
                    .firstName(authorEntity.get().getFirstName())
                    .lastName(authorEntity.get().getLastName())
                    .build();
        }

        throw new RuntimeException("Failed to find author with name " + firstname+" "+lastname);
    }

    public void addAuthor(final Author author) {
        final AuthorEntity authorEntity =
                AuthorEntity
                        .builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .build();

        Optional<AuthorEntity> existingBooks = authorRepository.findByFirstNameAndLastName(author.getFirstName(),author.getLastName());
        if (existingBooks.isPresent()) {
            throw new RuntimeException("A author with name " + author.getFirstName()+" " + author.getLastName() + " already exists.");
        } else {
            authorRepository.save(authorEntity);
        }
    }
}
