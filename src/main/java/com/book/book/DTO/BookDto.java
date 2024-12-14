package com.book.book.DTO;

import com.book.book.Models.Language;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(
        @JsonAlias({"title"})
        String title,
        @JsonAlias({"authors"})
        List<AuthorDto> author,
        @JsonAlias({"languages"})
        List<String> languages,
        @JsonAlias({"download_count"})
        Double downloads ) {


}
