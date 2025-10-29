/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.api.github.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase de utilidades para formatear texto y fechas
 * usadas en la interfaz o salida.
 */
public class Formatter {

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Devuelve un texto seguro, reemplazando null o vacío por "-" */
    public static String safe(String value) {
        return (value == null || value.isBlank()) ? "-" : value;
    }

    /** Formatea una fecha ISO (ej. 2025-10-29T11:30:00Z) a un formato legible */
    public static String formatDate(String isoDate) {
        try {
            if (isoDate == null || isoDate.isBlank()) return "-";
            OffsetDateTime odt = OffsetDateTime.parse(isoDate);
            return odt.format(OUTPUT_FORMAT);
        } catch (Exception e) {
            return "-";
        }
    }
}

