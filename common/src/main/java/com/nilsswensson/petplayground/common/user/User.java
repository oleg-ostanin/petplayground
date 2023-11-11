package com.nilsswensson.petplayground.common.user;

import com.nilsswensson.petplayground.common.token.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {


  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  private Role role;

  private List<Token> tokens;


}
