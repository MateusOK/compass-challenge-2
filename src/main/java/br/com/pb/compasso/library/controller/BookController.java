package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Integer bookId){
        bookService.delete(bookId);
    }

}