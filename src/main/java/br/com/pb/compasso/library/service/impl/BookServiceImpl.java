package br.com.pb.compasso.library.service.impl;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto update(Long id, BookResquestDto request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("book id not found" + id));

        book.setBookTitle(request.bookTitle());
        book.setAuthor(request.author());
        book.setRating(request.rating());
        book.setPages(request.pages());
        book.setGenre(request.genre());
        book.setReleaseDate(request.releaseDate());

        Book updatedBook = bookRepository.save(book);

        return new BookResponseDto(updatedBook);
    }
}
