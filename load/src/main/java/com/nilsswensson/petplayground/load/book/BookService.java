package com.nilsswensson.petplayground.load.book;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.model.book.Book;
import com.nilsswensson.petplayground.load.client.FacadeRestFeignClient;
import com.nilsswensson.petplayground.load.manager.ManagerService;
import com.nilsswensson.petplayground.load.utils.BookUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    private final ManagerService managerService;
    private final BookUtils bookUtils;
    private final FacadeRestFeignClient restClient;

    public BookService(ManagerService managerService, BookUtils bookUtils, FacadeRestFeignClient restClient) {
        this.managerService = managerService;
        this.bookUtils = bookUtils;
        this.restClient = restClient;
    }

    @Scheduled(fixedDelay = 10000000000L)
    public void addBook() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final List<Book> allBooks = bookUtils.getAllBooks();

        for(Book book : allBooks) {
            restClient.addBook(book, "Bearer " + authenticationResponse.getAccessToken());
        }
    }

    @Scheduled(initialDelay = 2000L, fixedDelay = 50000000000L)
    public void getBook() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final Book book = restClient.getBook("Bearer " + authenticationResponse.getAccessToken());

        log.info("Got book: {}", book.getTitle());
    }
}
