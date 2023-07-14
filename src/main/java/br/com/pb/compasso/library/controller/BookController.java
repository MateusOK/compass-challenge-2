package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;


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

    @PostMapping("/api/books")
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody @Valid BookResquestDto request, UriComponentsBuilder builder){
        var response = bookService.saveBook(request);
        var uri = builder.path("/api/books/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

   @GetMapping("/api/books/genre")
   public ResponseEntity<List<BookResponseDto>> findByGenre(@RequestParam("genre") String genre){
        var response = bookService.findByGenre(genre.toLowerCase());
        return ResponseEntity.ok(response);
   }

    @GetMapping("/api/books/author")
    public ResponseEntity<List<BookResponseDto>> findByAuthor(@RequestParam("author") String author) {
        var response = bookService.findByAuthor(author.toLowerCase());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/books/batch")
    public ResponseEntity<List<BookResponseDto>> saveMultipleBooks(@RequestBody @Valid List<BookResquestDto> request) {
        List<BookResponseDto> response = bookService.saveMultipleBooks(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    @PutMapping("api/books/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long bookId, @RequestBody BookResquestDto request){
        BookResponseDto updateBook = bookService.update(bookId, request);
        return ResponseEntity.ok(updateBook);
    }

}