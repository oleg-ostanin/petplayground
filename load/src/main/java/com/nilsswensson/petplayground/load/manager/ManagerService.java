package com.nilsswensson.petplayground.load.manager;

import com.nilsswensson.petplayground.common.auth.AuthenticationRequest;
import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import com.nilsswensson.petplayground.common.user.Role;
import com.nilsswensson.petplayground.common.user.StringWrapper;
import com.nilsswensson.petplayground.load.client.FacadeAuthFeignClient;
import org.springframework.stereotype.Service;

import static com.nilsswensson.petplayground.common.util.StringConstants.*;

@Service
public class ManagerService {

    private static final RegisterRequest DEFAULT_MANAGER_REQUEST = RegisterRequest.builder()
            .firstname("Default")
            .lastname("Manager")
            .email(DEFAULT_MANAGER_EMAIL)
            .password(DEFAULT_MANAGER_PASSWORD)
            .role(Role.MANAGER)
            .build();

    private final FacadeAuthFeignClient authClient;

    public ManagerService(FacadeAuthFeignClient authClient) {
        this.authClient = authClient;
    }

    public AuthenticationResponse authenticate() {
//        final String role = AuthUtils.whoami(WHO_AM_I, DEFAULT_MANAGER_EMAIL);

        final StringWrapper wrapper = StringWrapper.builder().content(DEFAULT_MANAGER_EMAIL).build();
        final String role = authClient.whoami(wrapper).getContent();

        if (role.equals(UNKNOWN_USER)) {
            return authClient.register(DEFAULT_MANAGER_REQUEST);
        }

        final AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email(DEFAULT_MANAGER_EMAIL)
                .password(DEFAULT_MANAGER_PASSWORD)
                .build();

        return authClient.authenticate(authenticationRequest);
    }
}
