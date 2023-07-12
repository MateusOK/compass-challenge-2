package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.model.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(Long.valueOf(id));
    }
}