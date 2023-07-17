package br.com.pb.compasso.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequestDto(

        @NotBlank(message = "{bookTitle.not.blank}")
        String bookTitle,

        @NotBlank(message = "{author.not.blank}")
        String author,

        @NotBlank(message = "{releaseDate.not.blank}")
        String releaseDate,

        @NotNull(message = "{pages.not.null}")
        Integer pages,

        @NotNull(message = "{rating.not.null}")
        Double rating,

        @NotBlank(message = "{genre.not.blank}")
        String genre
) {
}