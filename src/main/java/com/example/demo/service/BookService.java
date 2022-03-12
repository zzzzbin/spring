package com.example.demo.service;

import com.example.demo.domain.book.Book;
import com.example.demo.exception.BookNotFoundException;
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

    public double getPrice(int bookId) throws BookNotFoundException {
        Book book = null;
        double amount = 0;
        try {
            book = bookRepository.findById(bookId).orElse(null);
            amount = book.getPrice();
        } catch (Exception e) {
            throw new BookNotFoundException("Cannot not found book with id =" + bookId);
        }
        return amount;
    }

}
