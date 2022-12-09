package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.threads.Server;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ServerListController {
    public BorderPane serverListPane;
    public VBox serverList = new VBox();
    public ToolBar toolBar = new ToolBar();
    public Button refreshButton = new Button();
    public Button createServer = new Button();

    @FXML
    public void initialize() {
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
        });
        serverListPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            toolBar.setPrefHeight(serverListPane.getHeight() * 0.1);
            serverList.setPrefHeight(serverListPane.getHeight() * 0.9);

            refreshButton.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
            createServer.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
        });

        Server dummyServer = new Server("Test", "10.0.0.1", 1234, 2, 4, "Test map", "v0.0.1");
        Pane serverPane = dummyServer.getPane();
        serverPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        serverList.getChildren().add(serverPane);
    }

    private void refreshButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Refresh button clicked!");
        BombermanApplication.clientThread.send("getServers");
    }
}
