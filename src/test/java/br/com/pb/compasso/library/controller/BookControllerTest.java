package br.com.pb.compasso.library.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    public void bookTestFindAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
     public void testFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books","{bookId}"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void testFindByGenre() throws Exception{
        String genre="Fantasy";
        mockMvc.perform(get("/api/books/genre?genre=","{genre}"))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    public void testFindByAuthor() throws Exception{

        mockMvc.perform(get("/api/books/author?author=","{author}"))
                .andExpect(status().isOk()).andReturn();

    }
}