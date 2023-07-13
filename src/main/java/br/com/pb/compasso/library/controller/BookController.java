package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

}