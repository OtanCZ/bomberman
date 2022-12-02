package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainMenuController {

    public Button settingsButton;
    public Button playButton;

    public void settingsButtonOnClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.SETTINGS);
    }

    public void playButtonOnClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.GAME);
    }
}