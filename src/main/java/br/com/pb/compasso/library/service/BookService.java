package br.com.pb.compasso.library.service;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;

public interface BookService {

    BookResponseDto update(Long id, BookResquestDto request);

}