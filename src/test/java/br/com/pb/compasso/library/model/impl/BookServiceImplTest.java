package br.com.pb.compasso.library.model.impl;

import br.com.pb.compasso.library.domain.entity.Book;
import br.com.pb.compasso.library.model.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl service;
    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starBook();
    }

    @Test
    void whenFindAllThenReturnAnListOfBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> response = service.findAll();

        assertNotNull((response));
        assertEquals(1, response.size());
        assertEquals(Book.class, response.get(0).getClass());
    }

    @Test
    void whenFindByGenreThenReturnListOfBooks() {
        String genre = "fantasy";

        when(bookRepository.findByGenre(genre)).thenReturn(List.of(book));

        List<Book> result = service.findByGenre(genre);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(book, result.get(0));
    }

    @Test
    void whenFindByAuthorThenReturnListOfBooks(){
        String author = "JK Rowling";

        when(bookRepository.findByAuthor(author)).thenReturn(List.of(book));
        List<Book> response = service.findByAuthor(author);

        assertNotNull((response));
        assertEquals(1, response.size());
        assertEquals(Book.class, response.get(0).getClass());
    }


    private void starBook(){
        book = new Book(1L, "Harry Potter", "JK Rowling", "2000", 300, 9.5, "fantasy");
    }
}