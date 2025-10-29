/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.api.github.service;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Fábrica de clientes HTTP reutilizables.
 * Configura el timeout y las opciones básicas de conexión.
 */
public final class HTTPClientFactory {

    private HTTPClientFactory() {} // Evita instanciación

    public static HttpClient create() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
}

