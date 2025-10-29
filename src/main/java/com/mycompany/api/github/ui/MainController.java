/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.github.ui;

import com.mycompany.api.github.domain.Repo;
import com.mycompany.api.github.domain.User;
import com.mycompany.api.github.service.GitHubApi;
import com.mycompany.api.github.util.Formatter;
import com.mycompany.api.github.util.LanguageUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Map;

/**
 * Controlador del main.fxml
 */
public class MainController {

    @FXML private TextField txtUsername;
    @FXML private Button btnBuscar;
    @FXML private Label lblStatus;

    // Datos de usuario
    @FXML private ImageView imgAvatar;
    @FXML private Label lblName;
    @FXML private Label lblBio;
    @FXML private Label lblLocation;
    @FXML private Label lblFollowers;
    @FXML private Label lblCreated;

    // Tabla
    @FXML private TableView<Repo> tblRepos;
    @FXML private TableColumn<Repo, String> colName;
    @FXML private TableColumn<Repo, String> colDesc;

    // Gráfico
    @FXML private PieChart chartLanguages;

    private final GitHubApi api = new GitHubApi();
    
    @FXML
private void initialize() {
    // Configuración de la tabla
    colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
            Formatter.safe(c.getValue().getName())
    ));
    colDesc.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
            Formatter.safe(c.getValue().getLanguage())
    ));
    
    // Configuración robusta del PieChart
    chartLanguages.setMinHeight(300);
    chartLanguages.setMinWidth(400);
    chartLanguages.setLegendVisible(true);
    chartLanguages.setLabelsVisible(true);
    chartLanguages.setTitle("Lenguajes utilizados");
}


    @FXML
    private void onBuscar() {
        String username = txtUsername.getText().trim();
        if (username.isEmpty()) {
            lblStatus.setText("Ingrese un nombre de usuario");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        lblStatus.setText("Buscando...");
        lblStatus.setStyle("-fx-text-fill: black;");

        new Thread(() -> {
            try {
                User user = api.getUser(username);
                List<Repo> repos = api.getRepos(username);

                Platform.runLater(() -> updateUI(user, repos));
            } catch (Exception e) {
                Platform.runLater(() -> {
                    lblStatus.setText("Error: " + e.getMessage());
                    lblStatus.setStyle("-fx-text-fill: red;");
                });
            }
        }).start();
    }

    private void updateUI(User user, List<Repo> repos) {
        lblStatus.setText("OK (" + repos.size() + " repos)");
        lblStatus.setStyle("-fx-text-fill: green;");

        lblName.setText(Formatter.safe(user.getLogin()));
        lblBio.setText(Formatter.safe(user.getBio()));
        lblLocation.setText("Ubicación: " + Formatter.safe(user.getLocation()));
        lblFollowers.setText("Seguidores: " + user.getFollowers());
        lblCreated.setText(Formatter.formatDate(user.getCreatedAt()));

        // Avatar
        if (user.getAvatarUrl() != null && !user.getAvatarUrl().isBlank()) {
            imgAvatar.setImage(new Image(user.getAvatarUrl(), true));
        } else {
            imgAvatar.setImage(null);
        }

        // Tabla
        ObservableList<Repo> data = FXCollections.observableArrayList(repos);
        tblRepos.setItems(data);

        // Gráfico
        updateChart(repos);
    }
    
    private void updateChart(List<Repo> repos) {
    try {
        chartLanguages.getData().clear();
        
        // Debug: verificar qué datos llegan
        System.out.println("Repos recibidos: " + repos.size());
        repos.forEach(repo -> 
            System.out.println("Repo: " + repo.getName() + " - Language: " + repo.getLanguage())
        );
        
        Map<String, Double> percentages = LanguageUtils.languagePercentages(repos);
        System.out.println("Porcentajes calculados: " + percentages);
        
        if (percentages.isEmpty()) {
            chartLanguages.setTitle("No hay datos de lenguajes disponibles");
            // Agregar un dato dummy para que el chart se muestre
            PieChart.Data noData = new PieChart.Data("Sin datos", 100);
            chartLanguages.getData().add(noData);
            return;
        }
        
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : percentages.entrySet()) {
            String label = String.format("%s (%.1f%%)", entry.getKey(), entry.getValue());
            chartData.add(new PieChart.Data(label, entry.getValue()));
        }
        
        chartLanguages.setData(chartData);
        chartLanguages.setTitle("Lenguajes utilizados (" + repos.size() + " repos)");
        
        // Forzar actualización
        Platform.runLater(() -> {
            chartLanguages.requestLayout();
        });
        
    } catch (Exception e) {
        System.err.println("Error actualizando chart: " + e.getMessage());
        e.printStackTrace();
        chartLanguages.setTitle("Error cargando datos");
    }
}



}

