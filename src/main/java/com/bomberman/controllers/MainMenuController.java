package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.view.SceneEntity;
import com.bomberman.view.StageManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuController {
    public BorderPane mainMenuPane;
    public Pane logo = new Pane();
    public Button playButton = new Button();
    public Button settingsButton = new Button();
    public Button mapCreatorButton = new Button();
    public Button exitButton = new Button();
    public VBox buttons = new VBox();

    @FXML
    public void initialize() {
        System.out.println("MainMenuController initialized!");

        playButton.setText("Server Browser");
        playButton.setOnMouseClicked(mouseEvent -> {
            try {
                playButtonOnMouseClick(mouseEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        settingsButton.setText("Settings");
        settingsButton.setOnMouseClicked(mouseEvent -> {
            try {
                settingsButtonOnMouseClick(mouseEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        mapCreatorButton.setText("Map Creator");
        mapCreatorButton.setOnMouseClicked(this::mapCreatorButtonOnMouseClick);

        exitButton.setText("Exit");
        exitButton.setOnMouseClicked(this::exitButtonOnMouseClick);

        buttons.getChildren().addAll(playButton, settingsButton, mapCreatorButton, exitButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        mainMenuPane.setCenter(buttons);
        mainMenuPane.setTop(logo);

        mainMenuPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            logo.setPrefWidth(mainMenuPane.getWidth());
            buttons.setPrefWidth(mainMenuPane.getWidth());
        });
        mainMenuPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            logo.setPrefHeight(mainMenuPane.getHeight() * 0.2);
            buttons.setPrefHeight(mainMenuPane.getHeight() * 0.8);
        });
    }

    private void mapCreatorButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Map Creator button clicked!");
        try {
            BombermanApplication.stageManager.showScene(SceneEntity.MAP_CREATOR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void exitButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Goodbye!");
        BombermanApplication.stageManager.close();
    }

    private void settingsButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Settings button clicked!");
        try {
            BombermanApplication.stageManager.showScene(SceneEntity.SETTINGS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void playButtonOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("Play button clicked!");
        try {
            BombermanApplication.stageManager.showScene(SceneEntity.SERVER_LIST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}