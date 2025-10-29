/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.api.github.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.api.github.domain.Repo;
import com.mycompany.api.github.domain.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Clase principal que se comunica con la API pública de GitHub.
 * Usa HttpClient para obtener datos del usuario y sus repos públicos.
 */
public class GitHubApi {

    private static final String API_URL = "https://api.github.com";
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public GitHubApi() {
        this.httpClient = HTTPClientFactory.create();
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // ============================
    // Obtener perfil del usuario
    // ============================
    public User getUser(String username) throws IOException, InterruptedException {
        String url = API_URL + "/users/" + username;
        HttpRequest request = baseRequest(url).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponseStatus(response);

        return mapper.readValue(response.body(), User.class);
    }

    // ============================
    // Obtener repositorios públicos
    // ============================
    public List<Repo> getRepos(String username) throws IOException, InterruptedException {
        String url = API_URL + "/users/" + username + "/repos?per_page=100&sort=updated";
        HttpRequest request = baseRequest(url).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponseStatus(response);

        return mapper.readValue(response.body(), new TypeReference<>() {});
    }

    // ============================
    // Configura cabeceras comunes
    // ============================
    private HttpRequest.Builder baseRequest(String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "api-github-java-client");

        // Si el usuario tiene token (para evitar límite de peticiones)
        String token = System.getenv("GITHUB_TOKEN");
        if (token != null && !token.isBlank()) {
            builder.header("Authorization", "Bearer " + token);
        }

        return builder;
    }

    // ============================
    // Manejo de errores HTTP
    // ============================
    private void checkResponseStatus(HttpResponse<String> response) throws IOException {
        int status = response.statusCode();
        if (status == 404) {
            throw new IOException("Usuario o repos no encontrados (404)");
        } else if (status == 403) {
            throw new IOException("Límite de peticiones alcanzado (403)");
        } else if (status < 200 || status >= 300) {
            throw new IOException("Error HTTP " + status + ": " + response.body());
        }
    }
}

