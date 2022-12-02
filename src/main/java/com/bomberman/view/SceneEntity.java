package com.bomberman.view;

import com.bomberman.BombermanApplication;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum SceneEntity {
    MAIN_MENU("main_menu-view.fxml"),
    SETTINGS("settings-view.fxml"),
    GAME("game-view.fxml");

    private String fxmlPath;
    SceneEntity(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BombermanApplication.class.getResource(this.getFxmlPath()));
        Scene scene = new Scene(fxmlLoader.load());
        return scene;
    }
}
