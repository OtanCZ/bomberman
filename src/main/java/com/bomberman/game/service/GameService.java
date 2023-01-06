package com.bomberman.game.service;

import com.bomberman.BombermanApplication;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.GameState;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.view.SceneEntity;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class GameService {
    public static GridPane playerList;
    public static VBox serverList;
    public void updateWaitingLobby() {
        System.out.println(playerList.getChildren().size());
        playerList.getChildren().clear();
        System.out.println(BombermanApplication.clientThread.currentServer);

        BombermanApplication.clientThread.currentServer.clients.forEach((client) -> {
            playerList.add(new Text(client.toString()), 0, 0);
        });
    }

    public void refreshServerList() {
        serverList.getChildren().clear();
        BombermanApplication.clientThread.servers.forEach(server -> {
            Button button = new Button();
            button.setText(server.getIp());
            button.setPrefWidth(serverList.getPrefWidth());
            button.setPrefHeight(serverList.getPrefHeight() * 0.1);
            button.setAlignment(Pos.CENTER_LEFT);
            button.setTextFill(Color.BLACK);
            button.setOnMouseClicked(this::serverButtonOnMouseClick);
            serverList.getChildren().add(button);
        });
    }

    private void serverButtonOnMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        try {
            BombermanApplication.clientThread.connectToServer(button.getText(), 6969);
            BombermanApplication.stageManager.showScene(SceneEntity.WAITING_LOBBY);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
