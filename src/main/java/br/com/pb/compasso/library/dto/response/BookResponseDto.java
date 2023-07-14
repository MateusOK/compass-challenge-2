package br.com.pb.compasso.library.dto.response;

import br.com.pb.compasso.library.entity.Book;

public record BookResponseDto(
        Long id,

        String bookTitle,

        String author,

        String releaseDate,

        Integer pages,

        Double genre,

        String rating
) {

    public BookResponseDto(Book response){
        this(response.getId(), response.getBookTitle(), response.getAuthor(),
                response.getReleaseDate(), response.getPages(), response.getRating(), response.getGenre());
    }

}
