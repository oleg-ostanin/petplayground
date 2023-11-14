package com.nilsswensson.petplayground.facade.entity.book;

import com.nilsswensson.petplayground.facade.security.token.Token;
import com.nilsswensson.petplayground.facade.security.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
  private String title;
}
