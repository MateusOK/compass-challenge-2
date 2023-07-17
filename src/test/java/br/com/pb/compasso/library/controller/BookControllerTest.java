package br.com.pb.compasso.library.controller;

import br.com.pb.compasso.library.entity.Book;
import br.com.pb.compasso.library.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setup(){
//        bookRepository.deleteAll();
//    }

    @Test
    public void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception{

        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        ResultActions response = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookTitle",
                        is(book.getBookTitle())))
                .andExpect(jsonPath("$.author",
                        is(book.getAuthor())))
                .andExpect(jsonPath("$.releaseDate",
                        is(book.getReleaseDate())))
                .andExpect(jsonPath("$.pages",
                        is(book.getPages())))
                .andExpect(jsonPath("$.rating",
                        is(book.getRating())))
                .andExpect(jsonPath("$.genre",
                        is(book.getGenre())));

    }

    @Test
    public void givenListOfBooks_whenGetAllBooks_thenReturnBooksList() throws Exception{

        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build());

        listOfBooks.add(Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build());

        bookRepository.saveAll(listOfBooks);

        ResultActions response = mockMvc.perform(get("/api/books"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", notNullValue()));

    }

    @Test
    public void givenBookId_whenGetBookById_thenReturnBookObject() throws Exception{
        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/{id}", book.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookTitle",
                        is(book.getBookTitle())))
                .andExpect(jsonPath("$.author",
                        is(book.getAuthor())))
                .andExpect(jsonPath("$.releaseDate",
                        is(book.getReleaseDate())))
                .andExpect(jsonPath("$.pages",
                        is(book.getPages())))
                .andExpect(jsonPath("$.rating",
                        is(book.getRating())))
                .andExpect(jsonPath("$.genre",
                        is(book.getGenre())));

    }

    @Test
    public void givenBookGenre_whenGetBookByGenre_thenReturnBookObject() throws Exception{
        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/genre")
                .param("genre", book.getGenre()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", notNullValue()));

    }

    @Test
    public void givenBookAuthor_whenGetBookByAuthor_thenReturnBookObject() throws Exception{
        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/author")
                .param("author", book.getAuthor()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", notNullValue()));

    }

    @Test
    public void givenInvalidBookId_whenGetBookById_thenReturnIsNotFound() throws Exception{
        // given - precondition or setup
        long bookID = 60L;
        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/{id}", bookID));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void givenBookGenre_whenGetBookByGenre_thenReturnIsNotFound() throws Exception{

        String genre = "genre";

        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/genre")
                .param("genre", genre));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void givenBookAuthor_whenGetBookByAuthor_thenIsNotFound() throws Exception{

        String author = "test";

        Book book = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(book);

        ResultActions response = mockMvc.perform(get("/api/books/author")
                .param("author", author));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void givenUpdatedBook_whenUpdateBook_thenReturnUpdatedBookObject() throws Exception{
        Book savedBook = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(savedBook);

        Book updatedBook = Book.builder()
                .bookTitle("Harry Potter and the secret chamber")
                .author("j k rowling")
                .releaseDate("2005-02-02")
                .pages(600)
                .rating(9.7)
                .genre("fantasy")
                .build();

        ResultActions response = mockMvc.perform(put("/api/books/{id}", savedBook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)));


        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookTitle",
                        is(updatedBook.getBookTitle())))
                .andExpect(jsonPath("$.author",
                        is(updatedBook.getAuthor())))
                .andExpect(jsonPath("$.releaseDate",
                        is(updatedBook.getReleaseDate())))
                .andExpect(jsonPath("$.pages",
                        is(updatedBook.getPages())))
                .andExpect(jsonPath("$.rating",
                        is(updatedBook.getRating())))
                .andExpect(jsonPath("$.genre",
                        is(updatedBook.getGenre())));
    }


    @Test
    public void givenUpdatedBook_whenUpdateBook_thenReturnBadRequest() throws Exception{

        long bookID = 66L;
        Book savedBook = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(savedBook);

        Book updatedBook = Book.builder()
                .bookTitle("Harry Potter and the secret chamber")
                .author("j k rowling")
                .releaseDate("2005-02-02")
                .pages(600)
                .rating(9.7)
                .genre("fantasy")
                .build();

        ResultActions response = mockMvc.perform(put("/api/books/{id}", bookID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)));

        response.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void givenBookId_whenDeleteBook_thenReturn204() throws Exception{

        Book savedBook = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(savedBook);

        ResultActions response = mockMvc.perform(delete("/api/books/{id}", savedBook.getId()));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void givenBookId_whenDeleteBook_thenReturnIsBadRequest() throws Exception{

        Long testID = 66L;

        Book savedBook = Book.builder()
                .bookTitle("Harry Potter")
                .author("j k rowling")
                .releaseDate("2000-02-02")
                .pages(780)
                .rating(9.2)
                .genre("fantasy")
                .build();

        bookRepository.save(savedBook);

        ResultActions response = mockMvc.perform(delete("/api/books/{id}", testID));

        response.andExpect(status().isBadRequest())
                .andDo(print());
    }

}
