package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.model.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}