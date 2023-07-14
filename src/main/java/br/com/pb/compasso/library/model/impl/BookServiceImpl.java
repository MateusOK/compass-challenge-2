package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.domain.exception.PageNotFoundException;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Book findById(Integer id) {
        Optional<Book> result = bookRepository.findById(Long.valueOf(id));
        Book book;

        if (result.isPresent()){
            book = result.get();
        } else {
            throw new RuntimeException("Book id not found " + id);
        }

        return book;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByGenre(String genre) {

        List<Book> bookByGenreList = bookRepository.findByGenre(genre);

        if (bookByGenreList.isEmpty()){
            throw new PageNotFoundException("Book genre not found");
        }

        return bookByGenreList;

    }

    @Override
    public List<Book> findByAuthor(String author) {

        List<Book> bookByAuthorList = bookRepository.findByGenre(author);

        if (bookByAuthorList.isEmpty()){
            throw new PageNotFoundException("Book author not found");
        }

        return bookByAuthorList;
    }

    @Override
    public void delete(Integer id) {

        try{
            bookRepository.deleteById(Long.valueOf(id));
        }
        catch(PageNotFoundException e){
            throw new PageNotFoundException("Insert a valid Id");
        }
    }
}