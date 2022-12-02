package com.bomberman;

import com.bomberman.view.SceneEntity;
import com.bomberman.view.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class BombermanApplication extends Application {
    public static StageManager stageManager;
    @Override
    public void start(Stage stage) throws Exception {
        this.stageManager = new StageManager(stage);
        stageManager.showScene(SceneEntity.MAIN_MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}