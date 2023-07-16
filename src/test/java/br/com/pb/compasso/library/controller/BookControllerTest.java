package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.yml")
@SpringBootTest
class BookControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    private Book book;

    private BookResponseDto bookResponseDto;

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute("INSERT INTO book (book_title, author, release_date, pages, rating, genre) VALUES\n" +
                "('the great gatsby', 'f. scott fitzgerald', '1925-04-10', 218, 4.5, 'fiction')");
    }
    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute("DELETE FROM book");
    }

    @Test
    void findAll() {

        var response = bookServiceImpl.getAllBooks();

        assertEquals(bookResponseDto.getClass(), response.getClass());
        assertFalse(response.isEmpty());
        System.out.println(response);
    }

    @Test
    void findById() {
    }

    @Test
    void saveBook() {

    }

    @Test
    void findByGenre() {
    }

    @Test
    void findByAuthor() {
    }

    @Test
    void saveMultipleBooks() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}