package com.nilsswensson.petplayground.load.manager;

import com.nilsswensson.petplayground.common.auth.AuthenticationRequest;
import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import com.nilsswensson.petplayground.common.user.Role;
import com.nilsswensson.petplayground.common.user.StringWrapper;
import com.nilsswensson.petplayground.load.client.FacadeAuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nilsswensson.petplayground.common.util.StringConstants.*;
@Slf4j
@Service
public class ManagerService {

    private Long lastAuth = 0L;
    private String token;

    private static final RegisterRequest DEFAULT_MANAGER_REQUEST = RegisterRequest.builder()
            .firstname("Default")
            .lastname("Manager")
            .email(DEFAULT_MANAGER_EMAIL)
            .phone(DEFAULT_MANAGER_PHONE)
            .password(DEFAULT_MANAGER_PASSWORD)
            .role(Role.MANAGER)
            .build();

    private final FacadeAuthFeignClient authClient;

    public ManagerService(FacadeAuthFeignClient authClient) {
        this.authClient = authClient;
    }

    public AuthenticationResponse authenticate() {
        final StringWrapper wrapper = StringWrapper.builder().content(DEFAULT_MANAGER_EMAIL).build();
        final String role = authClient.whoami(wrapper).getContent();

        log.info("Got role for {}: {}", DEFAULT_MANAGER_EMAIL, role);

        if (role.equals(UNKNOWN_USER)) {
            log.info("Trying to register user with email {}", DEFAULT_MANAGER_REQUEST.getEmail());
            return authClient.register(DEFAULT_MANAGER_REQUEST);
        }

        final AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email(DEFAULT_MANAGER_EMAIL)
                .password(DEFAULT_MANAGER_PASSWORD)
                .build();

        return authClient.authenticate(authenticationRequest);
    }

    public String token() {
        long now = System.currentTimeMillis();
        if (now - lastAuth > 5000L || token == null) {
            token = "Bearer " + authenticate().getAccessToken();
            return token;
        }
        return token;
    }
}
