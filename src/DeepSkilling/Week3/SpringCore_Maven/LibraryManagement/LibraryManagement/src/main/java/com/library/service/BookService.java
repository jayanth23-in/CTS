package com.library.service;

import com.library.repository.BookRepository;

/**
 * Service layer - contains business logic.
 * BookRepository is injected by Spring via the setter method below
 * (see applicationContext.xml), so this class never creates its own
 * BookRepository with "new".
 */
public class BookService {

    private BookRepository bookRepository;

    // Setter used by Spring for Dependency Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookId, String bookTitle) {
        System.out.println("Service: Adding book...");
        bookRepository.addBook(bookId, bookTitle);
    }

    public String findBook(String bookId) {
        System.out.println("Service: Searching for book with ID " + bookId);
        return bookRepository.getBookById(bookId);
    }

    public void removeBook(String bookId) {
        System.out.println("Service: Removing book...");
        bookRepository.removeBook(bookId);
    }
}
