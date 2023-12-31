package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.dto.request.BookRequestDto;
import br.com.pb.compasso.library.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
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
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody @Valid BookRequestDto request, UriComponentsBuilder builder){
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
    public ResponseEntity<List<BookResponseDto>> saveMultipleBooks(@RequestBody @Valid List<BookRequestDto> request) {
        List<BookResponseDto> response = bookService.saveMultipleBooks(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("api/books/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable @Valid @NotNull Long bookId, @RequestBody @Valid @NotNull BookRequestDto request) {
        BookResponseDto updateBook = bookService.update(bookId, request);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/api/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable @Valid @Positive Long bookId){
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }

}