package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.tiles.TileEntity;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GameController {
    public BorderPane gameWindow;
    public VBox playerWindow = new VBox();
    public Canvas mapWindow = new Canvas();
    public VBox statsWindow = new VBox();

    @FXML
    public void initialize() {
        System.out.println("GameController initialized!");
        gameWindow.setLeft(playerWindow);
        gameWindow.setCenter(mapWindow);
        gameWindow.setRight(statsWindow);

        gameWindow.widthProperty().addListener((observable, oldValue, newValue) -> {
            mapWindow.setWidth(gameWindow.getWidth() * 0.8);
            statsWindow.setPrefWidth(gameWindow.getWidth() * 0.1);
            playerWindow.setPrefWidth(gameWindow.getWidth() * 0.1);
        });
        gameWindow.heightProperty().addListener((observable, oldValue, newValue) -> {
            mapWindow.setHeight(gameWindow.getHeight());
            statsWindow.setPrefHeight(gameWindow.getHeight());
            playerWindow.setPrefHeight(gameWindow.getHeight());
        });
    }

    public void mainMenuButtonOnMouseClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
    }

    public void onKeyPress(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        mapWindow.getGraphicsContext2D().fillRect(0, 0, mapWindow.getWidth(), mapWindow.getHeight());
        playerWindow.getChildren().add(new ImageView(TileEntity.BRICK.getTile().getTexture()));
    }
}
