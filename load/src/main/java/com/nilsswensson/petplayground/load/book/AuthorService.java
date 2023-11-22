package com.nilsswensson.petplayground.load.book;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.model.book.Author;
import com.nilsswensson.petplayground.load.client.FacadeRestFeignClient;
import com.nilsswensson.petplayground.load.manager.ManagerService;
import com.nilsswensson.petplayground.load.utils.AuthorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorService {

    private final ManagerService managerService;
    private final AuthorUtils authorUtils;
    private final FacadeRestFeignClient restClient;


    public AuthorService(ManagerService managerService, AuthorUtils authorUtils, FacadeRestFeignClient restClient) {
        this.managerService = managerService;
        this.authorUtils = authorUtils;
        this.restClient = restClient;
    }


    @Scheduled(initialDelay = 4000L,fixedDelay = 10000000000L)
    public void addAuthor() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final List<Author> authors = authorUtils.getAllAuthors();

        for(Author author : authors) {
            restClient.addAuthor(author, "Bearer " + authenticationResponse.getAccessToken());
        }
    }

    @Scheduled(initialDelay = 6000L, fixedDelay = 50000000000L)
    public void getBook() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final Author author = restClient.getAuthor("Bearer " + authenticationResponse.getAccessToken());

        log.info("Got author: {}", author.getFirstName()+" "+author.getLastName());
    }
}
