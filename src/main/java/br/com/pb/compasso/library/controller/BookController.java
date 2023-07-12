package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("api/books")
    public Book save(@RequestBody Book book){
        return bookService.save(book);
    }

    @PostMapping("api/books/batch")
    public List<Book> saveBooksBatch(@RequestBody List<Book> books) {
        return bookService.saveAll(books);
    }
}