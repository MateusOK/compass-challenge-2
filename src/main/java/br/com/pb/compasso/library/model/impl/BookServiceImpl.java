package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.domain.exception.PageNotFoundException;
import br.com.pb.compasso.library.model.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRepositoryImpl bookRepositoryImpl;

    public BookServiceImpl(BookRepository bookRepository, BookRepositoryImpl  bookRepositoryImpl) {
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

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByGenre(String genre) {

        List<Book> bookByGenreList = bookRepository.findByGenre(genre);

        if (bookByGenreList.size() == 0){
            throw new PageNotFoundException("Book genre not found");
        }

        return bookByGenreList;

    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(Long.valueOf(id));
    }
}