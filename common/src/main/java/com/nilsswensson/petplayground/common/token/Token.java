package com.nilsswensson.petplayground.common.token;

import com.nilsswensson.petplayground.common.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {


  public Integer id;

  public String token;

  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  public User user;
}
