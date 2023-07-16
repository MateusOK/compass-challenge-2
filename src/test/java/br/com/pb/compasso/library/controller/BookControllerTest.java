package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BookControllerTest {


    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookServiceImpl bookService;

    @Autowired
    private Book book;

    @BeforeAll
    public static void setup(){
        request = new MockHttpServletRequest();
        request.setParameter("bookTitle","Harry Potter2");
        request.setParameter("author","jk");
        request.setParameter("releaseDate","1960-07-11");
        request.setParameter("pages","500");
        request.setParameter("rating","4.5");
        request.setParameter("genre","fantasy");

    }



    @Test
    public void saveBookTest()  throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .param("bookTitle", request.getParameterValues("bookTitle"))
                .param("author", request.getParameterValues("author"))
                .param("releaseDate", request.getParameterValues("releaseDate"))
                .param("pages", request.getParameterValues("pages"))
                .param("rating", request.getParameterValues("rating"))
                .param("genre", request.getParameterValues("genre")))
                .andExpect(status().isOk()).andReturn();


        ModelAndView mav= mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"book");

        Book verifyBook = bookService.FindByAuthor("jk");

        assertNotNull(verifyBook,"Author Should be found");

    }

    @Test
    public void saveMultipleBooksTest() {
    }
}