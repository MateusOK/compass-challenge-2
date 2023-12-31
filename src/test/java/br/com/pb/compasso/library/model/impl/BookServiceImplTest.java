package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.dto.request.BookRequestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.exception.InternalServerException;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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

    private BookRequestDto bookDTO;
    private Book book;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        starBook();
    }

    @Test
    void whenFindByIdThenReturnAnExistingBook() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(book));


        BookResponseDto responseDto = bookService.findById(ID);

        assertNotNull(responseDto);
        assertEquals(BookResponseDto.class, responseDto.getClass());
        assertEquals(ID, responseDto.id());
        assertEquals("Harry Potter", responseDto.bookTitle());
        assertEquals("JK Rowling", responseDto.author());
        assertEquals("2000", responseDto.releaseDate());
        assertEquals(300, responseDto.pages());
        assertEquals(9.5, responseDto.rating());
        assertEquals("fantasy", responseDto.genre());
    }

    @Test
    void whenFindByAuthorThenReturnListOfBooksByAuthor(){
        String author = "JK Rowling";
        List<Book> books = Arrays.asList(
                new Book(1L, "Harry Potter", author, "2000", 300, 9.5, "fantasy"),
                new Book(2L, "Fantastic Beasts", author, "2016", 400, 8.8, "fantasy")
        );
        when(bookRepository.findByAuthor(author)).thenReturn(books);

        List<BookResponseDto> response = bookService.findByAuthor(author);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void whenFindByGenreThenReturnListOfBooksByGenre(){
        String author = "fantasy";
        List<Book> books = Arrays.asList(
                new Book(1L, "Harry Potter", author, "2000", 300, 9.5, "fantasy"),
                new Book(2L, "Fantastic Beasts", author, "2016", 400, 8.8, "fantasy")
        );

        when(bookRepository.findByGenre(genre)).thenReturn(books);

        List<BookResponseDto> response = bookService.findByGenre(genre);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void whenGetAllBooksThenReturnListOfAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Harry Potter", author, "2000", 300, 9.5, "fantasy"),
                new Book(2L, "Fantastic Beasts", author, "2016", 400, 8.8, "fantasy")
        );
        when(bookRepository.findAll()).thenReturn(books);


        List<BookResponseDto> response = bookService.getAllBooks();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
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
        BookRequestDto requestDto = new BookRequestDto("The Lord of the Rings", "J.R.R. Tolkien", "1954", 1200, 9.0, "Epic Fantasy");

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
        bookDTO = new BookRequestDto(bookTitle, author, releaseDate, pages, rating, genre);
    }
}