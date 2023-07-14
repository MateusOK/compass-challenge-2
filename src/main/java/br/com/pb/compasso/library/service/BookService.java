package br.com.pb.compasso.library.service;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto saveBook(BookResquestDto request);
    List<BookResponseDto> saveMultipleBooks(List<BookResquestDto> request);

}