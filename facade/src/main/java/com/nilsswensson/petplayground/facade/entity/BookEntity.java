package com.nilsswensson.petplayground.facade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
  @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @ManyToMany
  @JoinTable(
          name = "author_book",
          joinColumns = @JoinColumn(name = "author_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  private Set<AuthorEntity> authors;
}
