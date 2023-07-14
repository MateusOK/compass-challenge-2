package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookResponseDto>> findAll(){
        var response = bookService.getAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/books/{bookId}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long bookId){
        var response = bookService.findById(bookId);
        return ResponseEntity.ok(response);
    }

   @GetMapping("/api/books/genre")
   public ResponseEntity<List<BookResponseDto>> findByGenre(@RequestParam("genre") String genre){
        var response = bookService.findByGenre(genre);
        return ResponseEntity.ok(response);
   }

    @GetMapping("/api/books/author")
    public ResponseEntity<List<BookResponseDto>> findByAuthor(@RequestParam("author") String author){
        var response = bookService.findByAuthor(author);
        return ResponseEntity.ok(response);
    }

}