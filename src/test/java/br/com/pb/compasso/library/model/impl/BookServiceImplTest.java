package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.exception.InternalServerException;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.BookService;
import br.com.pb.compasso.library.service.impl.BookServiceImpl;
import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.client.ExpectedCount;

import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.times;

class BookServiceImplTest {

    private static final Long ID = 1L;
    private static final String bookTitle = "Harry Potter";
    private static final String author = "JK Rowling";
    private static final String releaseDate = "2000";
    private static final Integer pages = 300;
    private static final String genre = "fantasy";
    private static final Double rating = 9.5;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    private BookResquestDto bookDTO;
    private Book book;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        starBook();
    }


    @Test
    void whenSaveBookThenReturnsNewBook() {
        when(bookRepository.save(any())).thenReturn(book);

        BookResponseDto response = bookService.saveBook(bookDTO);

        assertNotNull(response);
        assertEquals(BookResponseDto.class, response.getClass());

        assertEquals(book.getId(), response.id());
        assertEquals(book.getBookTitle(), response.bookTitle());
        assertEquals(book.getAuthor(), response.author());
        assertEquals(book.getReleaseDate(), response.releaseDate());
    }

    @Test
    void whenSaveMultipleBooksThenReturnsNewBook() {
        when(bookRepository.saveAll(any())).thenReturn(List.of(book));

        List<BookResponseDto> response = bookService.saveMultipleBooks(List.of(bookDTO));

        assertNotNull(response);
        assertEquals(1, response.size());

        BookResponseDto savedBookResponse = response.get(0);
        assertEquals(book.getId(), savedBookResponse.id());
        assertEquals(book.getBookTitle(), savedBookResponse.bookTitle());
        assertEquals(book.getAuthor(), savedBookResponse.author());
        assertEquals(book.getReleaseDate(), savedBookResponse.releaseDate());
    }

    @Test
    void whenDeleteExistingBookThenNoExceptionThrown() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(book));

        assertDoesNotThrow(() -> bookService.delete(ID));

        verify(bookRepository).findById(ID);
        verify(bookRepository).delete(book);
    }

    @Test
    void whenDeleteNonExistingBookThenInternalServerExceptionThrown() {
        when(bookRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(InternalServerException.class, () -> bookService.delete(ID));
        verify(bookRepository).findById(ID);
    }

    @Test
    void whenUpdateBookThenReturnUpdatedBook() {
        Long id = 1L;
        BookResquestDto requestDto = new BookResquestDto("The Lord of the Rings", "J.R.R. Tolkien", "1954", 1200, 9.0, "Epic Fantasy");

        Book updatedBook = new Book(id, requestDto.bookTitle(), requestDto.author(), requestDto.releaseDate(),
                requestDto.pages(), requestDto.rating(), requestDto.genre());

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        BookResponseDto responseDto = bookService.update(id, requestDto);


        assertNotNull(responseDto);
        assertEquals(id, responseDto.id());
        assertEquals("The Lord of the Rings", responseDto.bookTitle());
        assertEquals("J.R.R. Tolkien", responseDto.author());
        assertEquals("1954", responseDto.releaseDate());
        assertEquals(1200, responseDto.pages());
        assertEquals("Epic Fantasy", responseDto.genre());
        assertEquals(9.0, responseDto.rating());

        verify(bookRepository).findById(id);
    }

    private void starBook() {
        book = new Book(ID, bookTitle, author, releaseDate, pages, rating, genre);
        bookDTO = new BookResquestDto(bookTitle, author, releaseDate, pages, rating, genre);
    }
}