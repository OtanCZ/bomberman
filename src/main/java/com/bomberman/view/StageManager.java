package com.bomberman.view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {
    private Stage stage;

    public StageManager(Stage stage) {
        this.stage = stage;
        stage.setMaximized(true);
    }

    public StageManager() {
        this.stage = new Stage();
    }

    public void showScene(SceneEntity sceneEntity) throws Exception {
        stage.setScene(sceneEntity.getScene());
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
