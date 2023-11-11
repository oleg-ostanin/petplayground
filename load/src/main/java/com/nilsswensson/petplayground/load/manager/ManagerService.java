package com.nilsswensson.petplayground.load.manager;

import com.nilsswensson.petplayground.common.auth.AuthenticationRequest;
import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import com.nilsswensson.petplayground.common.user.Role;
import com.nilsswensson.petplayground.load.utils.auth.AuthUtils;
import org.springframework.stereotype.Service;

import static com.nilsswensson.petplayground.common.util.StringConstants.*;
import static com.nilsswensson.petplayground.common.util.UriConstants.WHO_AM_I;

@Service
public class ManagerService {

    private static final RegisterRequest DEFAULT_MANAGER_REQUEST = RegisterRequest.builder()
            .firstname("Default")
            .lastname("Manager")
            .email(DEFAULT_MANAGER_EMAIL)
            .password(DEFAULT_MANAGER_PASSWORD)
            .role(Role.MANAGER)
            .build();

    public AuthenticationResponse authenticate() {
        final String role = AuthUtils.whoami(WHO_AM_I, DEFAULT_MANAGER_EMAIL);

        if (role.equals(UNKNOWN_USER)) {
            return AuthUtils.register(DEFAULT_MANAGER_REQUEST);
        }

        final AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email(DEFAULT_MANAGER_EMAIL)
                .password(DEFAULT_MANAGER_PASSWORD)
                .build();

        return AuthUtils.authenticate(authenticationRequest);
    }
}
