package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookService.findAll();
    }

   @GetMapping("/api/books/genre")
   public List<Book> findByGenre(@RequestParam("genre") String genre){
        return bookService.findByGenre(genre);
   }

    @GetMapping("/api/books/author")
    public List<Book> findByAuthor(@RequestParam("author") String author){
        return bookService.findByAuthor(author);
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