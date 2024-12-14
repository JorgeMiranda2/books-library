package com.book.book.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponseDto(
        int count,
        String next,
        String previous,
        List<BookDto> results
) {}
