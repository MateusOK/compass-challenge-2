package br.com.pb.compasso.library.dto.response;

import br.com.pb.compasso.library.entity.Book;

public record BookResponseDto(
        Long id,

        String bookTitle,

        String author,

        String releaseDate,

        Integer pages,

        String genre,

        Double rating
) {

    public BookResponseDto(Book response){
        this(response.getId(), response.getBookTitle(), response.getAuthor(),
                response.getReleaseDate(), response.getPages(), response.getGenre(), response.getRating());
    }

}
