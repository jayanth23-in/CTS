package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        // Load the Spring IoC container using the XML configuration
        ApplicationContext context =
                new ClassPathXmlApplicationContext("LibraryManagement/src/main/resources/applicationContext.xml");

        // Ask the container for the fully wired bookService bean
        BookService bookService = (BookService) context.getBean("bookService");

        bookService.addBook("B001", "Effective Java");
        bookService.addBook("B002", "Clean Code");

        String book = bookService.findBook("B001");
        System.out.println("Found book: " + book);

        bookService.removeBook("B002");
    }
}
