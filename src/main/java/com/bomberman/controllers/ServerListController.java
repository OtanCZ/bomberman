package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.server.ServerEntity;
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
            serverList.getChildren().clear();
            BombermanApplication.clientThread.discoverServers();
            Thread.sleep(100);
            refreshServerList(BombermanApplication.clientThread.servers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshServerList(List<ServerEntity> servers) {
        servers.forEach(server -> {
            Button button = new Button();
            button.setText(server.getIp());
            button.setPrefWidth(serverList.getPrefWidth());
            button.setPrefHeight(serverList.getPrefHeight() * 0.1);
            button.setAlignment(Pos.CENTER_LEFT);
            button.setTextFill(Color.BLACK);
            button.setOnMouseClicked(this::serverButtonOnMouseClick);
            serverList.getChildren().add(button);
        });
    }

    private void serverButtonOnMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        try {
            BombermanApplication.clientThread.connectToServer(button.getText(), 6969);
            BombermanApplication.stageManager.showScene(SceneEntity.WAITING_LOBBY);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
