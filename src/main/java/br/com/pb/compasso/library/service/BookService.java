package br.com.pb.compasso.library.service;

import br.com.pb.compasso.library.domain.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> saveAll(List<Book> books);

    Book findById(Integer id);

    List<Book> findAll();

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    void delete(Integer id);

}