package com.book.book.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDto(
        @JsonAlias({"author_name"})
        String name,

        @JsonAlias({"birth_year"})
        Long birthYear,

        @JsonAlias({"death_year"})
        Long deathYear
) {

}
