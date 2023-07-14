package br.com.pb.compasso.library.service.impl;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto saveBook(BookResquestDto request) {
        var response = bookRepository.save(new Book(request));
        return new BookResponseDto(response);
    }

    @Override
    public List<BookResponseDto> saveMultipleBooks(List<BookResquestDto> request) {
        List<Book> books = request.stream()
                .map(Book::new)
                .toList();

        List<Book> savedBooks = bookRepository.saveAll(books);

        return savedBooks.stream()
                .map(BookResponseDto::new)
                .toList();
    }


}
