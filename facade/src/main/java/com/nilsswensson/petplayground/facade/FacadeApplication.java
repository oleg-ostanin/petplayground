package com.nilsswensson.petplayground.facade;

import com.nilsswensson.petplayground.facade.security.auth.RegisterRequest;
import com.nilsswensson.petplayground.facade.security.user.Role;
import com.nilsswensson.petplayground.facade.security.auth.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FacadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacadeApplication.class, args);
	}

	//@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)
					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(Role.MANAGER)
					.build();
//			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
