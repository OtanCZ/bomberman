package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.game.service.GameService;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class ServerListController {
    public BorderPane serverListPane;
    public VBox serverList = new VBox();
    public ToolBar toolBar = new ToolBar();
    public Button refreshButton = new Button();
    public Button createServer = new Button();


    @FXML
    public void initialize() throws IOException {
        GameService.serverList = serverList;
        System.out.println("ServerListController initialized!");
        refreshButton.setText("♻️");
        refreshButton.setOnMouseClicked(this::refreshButtonOnMouseClick);
        refreshButton.setTooltip(new Tooltip("Refresh"));
        createServer.setText("➕");
        createServer.setTooltip(new Tooltip("Create server"));

        toolBar.getItems().addAll(refreshButton, createServer);

        serverListPane.setTop(toolBar);
        serverListPane.setCenter(serverList);

        serverListPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            serverList.setPrefWidth(serverListPane.getWidth());
            toolBar.setPrefWidth(serverListPane.getWidth());
            serverList.getChildren().forEach(node -> {
                Button button = (Button) node;
                button.setPrefWidth(serverListPane.getWidth());
            });
        });

        serverListPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            toolBar.setPrefHeight(serverListPane.getHeight() * 0.1);
            serverList.setPrefHeight(serverListPane.getHeight() * 0.9);
            refreshButton.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
            createServer.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
        });

        refreshButtonOnMouseClick(null);
    }

    private void refreshButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Refreshing servers!");
        try {
            BombermanApplication.clientThread.discoverServers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
