package com.example.demo.controller;

import com.example.demo.domain.book.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api(value = "Book Service", description = "My Book Store")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/save")
    @ApiOperation(value = "Store book api")
    public String saveBook(Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/searchBook/{bookId}")
    @ApiOperation(value = "Search book api")
    public Book getBook(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    @ApiOperation(value = "Delete book api")
    public List<Book> deleteBook(int bookId) {
        return bookService.removeBook(bookId);
    }

    @GetMapping("/searchBook/{bookId}/price")
    public double testGetPrice(@PathVariable int bookId) throws BookNotFoundException {
       return bookService.getPrice(bookId);
    }
}
