package br.com.pb.compasso.library.service.impl;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.exception.BadRequestException;
import br.com.pb.compasso.library.exception.InternalServerException;
import br.com.pb.compasso.library.exception.PageNotFoundException;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Validated
    public BookResponseDto saveBook(BookResquestDto request) {
        var response = bookRepository.save(new Book(request));
        return new BookResponseDto(response);
    }

    @Override
    @Validated
    public List<BookResponseDto> saveMultipleBooks(@Valid @NotNull List<BookResquestDto> request) {
        List<Book> books = request.stream()
                .map(Book::new)
                .toList();

        List<Book> savedBooks = bookRepository.saveAll(books);

        return savedBooks.stream()
                .map(BookResponseDto::new)
                .toList();
    }



    public BookResponseDto findById(Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            return new BookResponseDto(book.getId(), book.getBookTitle(), book.getAuthor(),
                    book.getReleaseDate(), book.getPages(), book.getRating(), book.getGenre());
        }
        else {
            throw new InternalServerException("Book ID not found - " + id);
        }
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        var response = bookRepository.findAll();
        var books = new ArrayList<BookResponseDto>();
        response.forEach(book -> books.add(new BookResponseDto(book)));
        if(response.isEmpty()){
            throw new PageNotFoundException("List of books is empty!");
        }
        return books;
    }

    @Override
    public List<BookResponseDto> findByGenre(String genre) {
        var response = bookRepository.findByGenre(genre);
        var books = new ArrayList<BookResponseDto>();
        response.forEach(book -> books.add(new BookResponseDto(book)));
        if(books.isEmpty()){
            throw new BadRequestException("There is no books with this genre - " + genre);
        }
        return books;
    }

    @Override
    public List<BookResponseDto> findByAuthor(String author) {
        var response = bookRepository.findByAuthor(author);
        var books = new ArrayList<BookResponseDto>();
        response.forEach(book -> books.add(new BookResponseDto(book)));
        if(books.isEmpty()){
            throw new BadRequestException("There is no books with this author - " + author);
        }
        return books;
    }
    @Override
    public BookResponseDto update(Long id, BookResquestDto request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new InternalServerException("Book ID not found - "+ id));

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
