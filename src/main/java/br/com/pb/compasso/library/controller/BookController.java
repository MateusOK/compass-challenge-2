package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}