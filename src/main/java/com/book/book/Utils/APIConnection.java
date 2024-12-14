package com.book.book.Utils;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class APIConnection {

    private final HttpClient client;
    private final String apiHost;

    public APIConnection() {
        client = HttpClient.newHttpClient();
        apiHost = "http://gutendex.com";
    }
    private HttpRequest createRequest(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(this.apiHost + path))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    public String getBookBySearch(String search) {
        String path =   "/books/?search=" + search.replaceAll(" ", "+");
        System.out.println("path: " + path);
        HttpRequest request = createRequest(path);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("Fallo en la Api: " + e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

}
