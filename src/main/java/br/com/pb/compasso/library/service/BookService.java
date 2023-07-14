package br.com.pb.compasso.library.service;


import br.com.pb.compasso.library.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto findById(Long id);

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> findByGenre(String genre);

    List<BookResponseDto> findByAuthor(String author);

}