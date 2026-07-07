package com.library.repository;

import java.util.HashMap;
import java.util.Map;

//In memory rather than using a DB
public class BookRepository {

    private Map<String, String> books = new HashMap<>();

    public void addBook(String bookId, String bookTitle) {
        books.put(bookId, bookTitle);
        System.out.println("Book added to repository: " + bookTitle);
    }

    public String getBookById(String bookId) {
        return books.get(bookId);
    }

    public void removeBook(String bookId) {
        String removed = books.remove(bookId);
        if (removed != null) {
            System.out.println("Book removed from repository: " + removed);
        }
    }
}
