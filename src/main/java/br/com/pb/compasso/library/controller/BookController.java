package br.com.pb.compasso.library.controller;
import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/")
@Validated
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/api/books")
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody BookResquestDto request, UriComponentsBuilder builder){
        var response = bookService.saveBook(request);
        var uri = builder.path("/api/books/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/api/books/batch")
    public ResponseEntity<List<BookResponseDto>> saveMultipleBooks(@RequestBody List<BookResquestDto> request) {
        List<BookResponseDto> response = bookService.saveMultipleBooks(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}