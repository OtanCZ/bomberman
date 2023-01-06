package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.tiles.TileEntity;
import com.bomberman.game.server.GameState;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
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
    public void initialize() throws InterruptedException {
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

        while(BombermanApplication.clientThread.currentServer.gameState == GameState.INGAME){
            for (TileEntity tile[][] : BombermanApplication.clientThread.currentServer.map.getMap()) {
                for (TileEntity tileEntity[] : tile) {
                    for (TileEntity tileEntity1 : tileEntity) {
                        if (tileEntity1 != null) {
                            Image tileImage = new Image(BombermanApplication.class.getResource(tileEntity1.getTile().getPathTotexture()).toString());
                            ImageView tileImageView = new ImageView(tileImage);
                            tileImageView.setFitWidth(mapWindow.getWidth() / BombermanApplication.clientThread.currentServer.map.getMap().length);
                            tileImageView.setFitHeight(mapWindow.getHeight() / BombermanApplication.clientThread.currentServer.map.getMap()[0].length);
                            mapWindow.getGraphicsContext2D().drawImage(tileImageView.getImage(), tileImageView.getFitWidth(), tileImageView.getFitHeight(), tileImageView.getFitWidth(), tileImageView.getFitHeight());
                        }
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public void mainMenuButtonOnMouseClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
    }

    public void onKeyPress(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        mapWindow.getGraphicsContext2D().fillRect(0, 0, mapWindow.getWidth(), mapWindow.getHeight());
    }
}
