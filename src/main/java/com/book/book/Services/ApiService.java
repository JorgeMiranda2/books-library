package com.book.book.Services;

import com.book.book.DTO.ApiResponseDto;
import com.book.book.DTO.BookDto;
import com.book.book.Utils.APIConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiService {

    private final APIConnection apiConnection;

    @Autowired
    public ApiService(APIConnection apiConnection) {
        this.apiConnection = apiConnection;
    }

    public ApiResponseDto searchBooks(String query) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse;
        try {
            // Verifica que la respuesta JSON esté bien formateada antes de procesarla
            jsonResponse = apiConnection.getBookBySearch(query);
            return mapper.readValue(jsonResponse, ApiResponseDto.class);
        } catch (IOException e) {
            // Manejo de errores en la deserialización
            System.err.println("Error al deserializar la respuesta JSON: " + e.getMessage());
            //e.printStackTrace();
            return null; // O lanzar una excepción personalizada, según el diseño de tu app
        }
    }

}
