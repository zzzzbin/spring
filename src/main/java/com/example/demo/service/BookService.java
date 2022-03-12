package com.example.demo.service;

import com.example.demo.domain.book.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public String saveBook(Book book) {
        bookRepository.save(book);
        return "book save with id" + book.getBookId();
    }

    public Book getBook(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> removeBook(int bookId) {
        bookRepository.deleteById(bookId);
        return bookRepository.findAll();
    }
}
