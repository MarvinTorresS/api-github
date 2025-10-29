/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.api.github.domain;

public class Repo {
    private String name;
    private String language;
    private int stargazersCount;
    private int forksCount;
    private String updatedAt;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getStargazersCount() { return stargazersCount; }
    public void setStargazersCount(int stargazersCount) { this.stargazersCount = stargazersCount; }

    public int getForksCount() { return forksCount; }
    public void setForksCount(int forksCount) { this.forksCount = forksCount; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}

