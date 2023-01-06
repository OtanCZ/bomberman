package com.bomberman;

import com.bomberman.game.server.socket.ClientSocket;
import com.bomberman.game.service.GameService;
import com.bomberman.view.SceneEntity;
import com.bomberman.view.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class BombermanApplication extends Application {
    public static StageManager stageManager;
    public static ClientSocket clientThread;
    public static GameService gameService;
    @Override
    public void start(Stage stage) throws Exception {
        this.stageManager = new StageManager(stage);
        this.clientThread = new ClientSocket();
        this.gameService = new GameService();

        stageManager.showScene(SceneEntity.MAIN_MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}