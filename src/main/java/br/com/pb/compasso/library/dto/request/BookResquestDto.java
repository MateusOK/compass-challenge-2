package br.com.pb.compasso.library.dto.request;

public record BookResquestDto(
        String bookTitle,

        String author,

        String releaseDate,

        Integer pages,

        String genre,

        Double rating
) {
}