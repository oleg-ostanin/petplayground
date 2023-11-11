package com.nilsswensson.petplayground.load.book;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.model.Book;
import com.nilsswensson.petplayground.load.manager.ManagerService;
import com.nilsswensson.petplayground.load.utils.BookUtils;
import com.nilsswensson.petplayground.load.utils.WebUtils;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final ManagerService managerService;
    private final BookUtils bookUtils;

    @Scheduled(fixedDelay = 10000000000L)
    public void addBook() {
        final AuthenticationResponse authenticationResponse = managerService.authenticate();

        final List<Book> allBooks = bookUtils.getAllBooks();

        for(Book book : allBooks) {
            WebUtils.post(authenticationResponse, "/add-book", book);
        }
    }
}
