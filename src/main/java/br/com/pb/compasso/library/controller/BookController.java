package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.domain.exception.PageNotFoundException;
import br.com.pb.compasso.library.service.BookService;
import jakarta.validation.constraints.Positive;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@Validated
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("api/books")
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/api/books/{bookId}")
    public Book findById(@PathVariable Integer bookId) {
        return bookService.findById(bookId);
    }

    @GetMapping("/api/books/genre")
    public List<Book> findByGenre(@RequestParam("genre") String genre) {
        return bookService.findByGenre(genre.toLowerCase(Locale.ROOT));
    }

    @GetMapping("/api/books/author")
    public List<Book> findByAuthor(@RequestParam("author") String author) {
        return bookService.findByAuthor(author.toLowerCase(Locale.ROOT));
    }

    @PostMapping("api/books")
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("api/books/batch")
    public List<Book> saveBooksBatch(@RequestBody List<Book> books) {
        return bookService.saveAll(books);
    }

    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable @Positive(message = "Book ID must be greater than zero") Integer bookId) {

        try {
            Book testIfBookOnDB = bookService.findById(bookId);
        }
        catch(RuntimeException e){
            throw new PageNotFoundException("Book Id not found on DataBase");
        }

        bookService.delete(bookId);
    }

}