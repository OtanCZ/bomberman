package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    public Button mainMenuButton;

    public void mainMenuButtonOnClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
    }
}