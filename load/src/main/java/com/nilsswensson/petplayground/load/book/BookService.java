package com.nilsswensson.petplayground.load.book;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.model.book.Author;
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

    //@Scheduled(fixedDelay = 10000000000L)
    public void addBookOld() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final List<Book> allBooks = bookUtils.getAllBooks();

        for(Book book : allBooks) {
            restClient.addBook(book, "Bearer " + authenticationResponse.getAccessToken());
        }
    }

    public void addBook(Book book) {
        restClient.addBook(book, token());
    }

    //@Scheduled(initialDelay = 2000L, fixedDelay = 50000000000L)
    public void getBook() {

        final Book book = restClient.getBook(token());

        log.info("Got book: {}", book.getTitle());
    }

    public List<Book> getBooks() {
        return restClient.getBooks(token());
    }

    //@Scheduled(initialDelay = 8000L, fixedDelay = 50000000000L)
    public void attachAuthor() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();
        final Author author = restClient.getAuthor("Bearer " + authenticationResponse.getAccessToken());
        restClient.attachAuthor(1L,author,"Bearer " + authenticationResponse.getAccessToken());
    }

    private String token() {
        return managerService.token();
    }

}
