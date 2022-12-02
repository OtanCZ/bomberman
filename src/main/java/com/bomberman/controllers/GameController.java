package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.view.SceneManager;
import com.bomberman.game.view.SimpleViewManager;
import com.bomberman.view.SceneEntity;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GameController {
    private final SceneManager sceneManager = new SimpleViewManager();
    public Button mainMenuButton;

    {
        System.out.println("GameController created");
        Rectangle r = new Rectangle(100, 100);
        sceneManager.showOnlySceneCollection(r);
    }

    public void mainMenuButtonOnMouseClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
    }
}
