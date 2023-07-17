package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.dto.request.BookResquestDto;
import br.com.pb.compasso.library.dto.response.BookResponseDto;
import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.repository.BookRepository;
import br.com.pb.compasso.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.yml")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Mock
    private BookServiceImpl bookServiceImplMock;

    private Book book;

    private BookRepository bookRepository;

    private BookResponseDto bookResponseDto;

    @BeforeEach
    public void beforeEach(){
        jdbc.execute("INSERT INTO book (book_title, author, release_date, pages, rating, genre) VALUES\n" +
                "('the great gatsby', 'f. scott fitzgerald', '1925-04-10', 218, 4.5, 'fiction')");
    }

    @Test
    void findAll() throws Exception {

        var response = bookServiceImplMock.getAllBooks();

//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
//                .andExpect(status().isOk()).andReturn();

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