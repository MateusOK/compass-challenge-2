package br.com.pb.compasso.library.service;


import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.dto.request.BookResquestDto;

import java.util.List;

public interface BookService {

    BookResponseDto saveBook(BookResquestDto request);
    List<BookResponseDto> saveMultipleBooks(List<BookResquestDto> request);
    BookResponseDto findById(Long id);

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> findByGenre(String genre);

    List<BookResponseDto> findByAuthor(String author);
    BookResponseDto update(Long id, BookResquestDto request);
    void delete(Long id);

}