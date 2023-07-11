package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.model.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}