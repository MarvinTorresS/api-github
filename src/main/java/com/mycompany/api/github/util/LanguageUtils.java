/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.api.github.util;

import com.mycompany.api.github.domain.Repo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Herramientas para analizar los lenguajes de los repositorios.
 */
public class LanguageUtils {
    public static Map<String, Double> languagePercentages(List<Repo> repos) {
        Map<String, Integer> languageCounts = new HashMap<>();
        int total = 0;
        
        // Contar lenguajes
        for (Repo repo : repos) {
            String language = repo.getLanguage();
            if (language != null && !language.trim().isEmpty()) {
                languageCounts.put(language, 
                    languageCounts.getOrDefault(language, 0) + 1);
                total++;
            }
        }
        
        // Calcular porcentajes
        Map<String, Double> percentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : languageCounts.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / total;
            percentages.put(entry.getKey(), percentage);
        }
        
        return percentages;
    }
}

