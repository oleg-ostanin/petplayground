package com.nilsswensson.petplayground.load.client;

import com.nilsswensson.petplayground.common.model.book.Book;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestBody;


public interface FacadeRestFeignClient {

    @RequestLine("POST /add-book")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"}
    )
    @Timed
    void addBook(@RequestBody Book book, @Param("token") String token);

    @RequestLine("GET /book")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"}
    )
    @Timed
    Book getBook(@Param("token") String token);
}
