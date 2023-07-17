package br.com.pb.compasso.library.service;


import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.dto.request.BookRequestDto;

import java.util.List;

public interface BookService {

    BookResponseDto saveBook(BookRequestDto request);
    List<BookResponseDto> saveMultipleBooks(List<BookRequestDto> request);
    BookResponseDto findById(Long id);

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> findByGenre(String genre);

    List<BookResponseDto> findByAuthor(String author);
    BookResponseDto update(Long id, BookRequestDto request);
    void delete(Long id);

}