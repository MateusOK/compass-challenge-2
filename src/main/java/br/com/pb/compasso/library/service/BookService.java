package br.com.pb.compasso.library.service;


import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookResponseDto saveBook(BookResquestDto request);
    List<BookResponseDto> saveMultipleBooks(List<BookResquestDto> request);
    BookResponseDto findById(Long id);

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> findByGenre(String genre);

    List<BookResponseDto> findByAuthor(String author);

}