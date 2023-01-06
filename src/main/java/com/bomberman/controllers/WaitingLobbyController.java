package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.GameState;
import com.bomberman.game.service.GameService;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WaitingLobbyController {
    public BorderPane waitingLobbyPane;
    public GridPane playerList = new GridPane();
    public ToolBar toolBar = new ToolBar();
    public VBox playerTopLeft = new VBox();
    public VBox playerTopRight = new VBox();
    public VBox playerBottomLeft = new VBox();
    public VBox playerBottomRight = new VBox();
    public Button leaveButton = new Button();
    public Button readyButton = new Button();
    public Button updateButton = new Button();

    @FXML
    public void initialize() {
        GameService.playerList = playerList;
        System.out.println("WaitingLobbyController initialized!");
        leaveButton.setText("⬅");
        leaveButton.setOnMouseClicked(this::leaveButtonOnMouseClick);
        leaveButton.setTooltip(new Tooltip("Leave server"));
        readyButton.setText("✅");
        readyButton.setTooltip(new Tooltip("Ready up"));
        readyButton.setOnMouseClicked(this::readyButtonOnMouseClick);
        updateButton.setText("🔄");
        updateButton.setTooltip(new Tooltip("Update player list"));
        updateButton.setOnMouseClicked(this::updateButtonOnMouseClick);


        toolBar.getItems().addAll(leaveButton, readyButton, updateButton);

        waitingLobbyPane.setTop(toolBar);
        waitingLobbyPane.setCenter(playerList);

        waitingLobbyPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            playerList.setPrefWidth(waitingLobbyPane.getWidth());
            toolBar.setPrefWidth(waitingLobbyPane.getWidth());
        });

        waitingLobbyPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            toolBar.setPrefHeight(waitingLobbyPane.getHeight() * 0.1);
            playerList.setPrefHeight(waitingLobbyPane.getHeight() * 0.9);
            readyButton.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
            leaveButton.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
            updateButton.setPrefSize(toolBar.getPrefHeight() * 0.8, toolBar.getPrefHeight() * 0.8);
            playerTopLeft.setPrefSize(playerList.getPrefWidth() * 0.5, playerList.getPrefHeight() * 0.5);
            playerTopRight.setPrefSize(playerList.getPrefWidth() * 0.5, playerList.getPrefHeight() * 0.5);
            playerBottomLeft.setPrefSize(playerList.getPrefWidth() * 0.5, playerList.getPrefHeight() * 0.5);
            playerBottomRight.setPrefSize(playerList.getPrefWidth() * 0.5, playerList.getPrefHeight() * 0.5);
        });
    }

    private void updateButtonOnMouseClick(MouseEvent mouseEvent) {
        BombermanApplication.gameService.updateWaitingLobby();
    }

    private void readyButtonOnMouseClick(MouseEvent mouseEvent) {
        BombermanApplication.clientThread.readyUp();
    }

    private void leaveButtonOnMouseClick(MouseEvent mouseEvent) {
        BombermanApplication.clientThread.leaveServer();
    }
}
