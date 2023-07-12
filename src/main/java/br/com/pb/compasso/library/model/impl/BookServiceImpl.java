package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.model.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRepositoryImpl bookRepositoryImpl;

    public BookServiceImpl(BookRepository bookRepository, BookRepositoryImpl bookRepositoryImpl) {
        this.bookRepository = bookRepository;
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepositoryImpl.saveAll(books);
    }
}