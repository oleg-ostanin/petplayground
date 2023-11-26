package com.nilsswensson.petplayground.load.client;

import com.nilsswensson.petplayground.common.model.book.Author;
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


    @RequestLine("POST /add-author")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"}
    )
    @Timed
    void addAuthor(@RequestBody Author author, @Param("token") String token);

    @RequestLine("GET /author")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"}
    )
    @Timed
    Author getAuthor(@Param("token") String token);

    @RequestLine("POST /attach-author/book/{id}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"}
    )
    @Timed
    void attachAuthor(@Param("id") Long id, @RequestBody Author author, @Param("token") String token);










}
