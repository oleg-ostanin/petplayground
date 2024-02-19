package com.nilsswensson.petplayground.load.auth;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.common.user.Role;
import com.nilsswensson.petplayground.load.utils.WebUtils;
import com.nilsswensson.petplayground.load.utils.auth.AuthUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class AuthTest {

//    @Test
    public void register() throws IOException {
        final long randLong = ThreadLocalRandom.current().nextLong();
        final String email = String.format("admin%d@mail.com", randLong);

        RegisterRequest admin = RegisterRequest.builder()
                .firstname("Admin")
                .lastname("Admin")
                .email(email)
                .password("password")
                .role(Role.ADMIN)
                .build();

        final AuthenticationResponse response = AuthUtils.register(admin);

        System.out.println(response.getAccessToken());

        final Book book = Book.builder().title("Hate").build();

        //WebUtils.post(response, "/add-book", book);

        String bookStr = WebUtils.get(response, "/book");

        System.out.println(bookStr);
    }


}
