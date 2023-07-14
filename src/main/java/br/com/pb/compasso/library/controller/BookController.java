package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PutMapping("api/books/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long bookId, @RequestBody BookResquestDto request){
        BookResponseDto updateBook = bookService.update(bookId, request);
        return ResponseEntity.ok(updateBook);
    }

}