package br.com.pb.compasso.library.service;

import br.com.pb.compasso.library.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    List<Book> saveAll(List<Book> books);

    Book findById(Integer id);

    List<Book> findAll();

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    void delete(Integer id);

}