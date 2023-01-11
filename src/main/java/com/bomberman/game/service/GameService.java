package com.bomberman.game.service;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.Map;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.GameState;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.view.SceneEntity;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    public static GridPane playerList;
    public static VBox serverList;
    public static BorderPane gameWindow;
    public void updateWaitingLobby() {
        System.out.println(playerList.getChildren().size());
        playerList.getChildren().clear();

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

    public static List<Map> loadMaps() {
        List<Map> maps = new ArrayList<>();
        try {
            if(BombermanApplication.class.getResource("maps/") != null){
                File defaultMapsFolder = new File(BombermanApplication.class.getResource("maps/").getFile());
                File[] mapsFiles = defaultMapsFolder.listFiles();
                for (File mapFile : mapsFiles) {
                    FileInputStream fileInputStream = new FileInputStream(mapFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    Map map = (Map) objectInputStream.readObject();
                    maps.add(map);
                }
            }

            File customMapsFolder = new File(System.getProperty("user.home") + "/.bomberman/maps/");
            File[] customMapsFiles = customMapsFolder.listFiles();
            for (File customMapFile : customMapsFiles) {
                FileInputStream fileInputStream = new FileInputStream(customMapFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Map map = (Map) objectInputStream.readObject();
                maps.add(map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return maps;
    }

    public void updateGame() {
        Canvas mapView = (Canvas) gameWindow.getCenter();
        BombermanApplication.clientThread.currentServer.map.draw(mapView.getGraphicsContext2D());
    }
}
