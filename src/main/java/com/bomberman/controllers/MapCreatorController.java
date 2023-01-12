package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.Map;
import com.bomberman.game.map.tiles.Tile;
import com.bomberman.game.map.tiles.TileEntity;
import com.bomberman.game.server.GameState;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MapCreatorController {
    public BorderPane mapCreatorPane;
    public VBox settingsWindow = new VBox();
    public Canvas mapWindow = new Canvas();
    public VBox tileWindow = new VBox();
    public TextField mapNameField = new TextField();
    public TextField mapWidthField = new TextField();
    public TextField mapHeightField = new TextField();
    public TextField mapDepthField = new TextField();
    public TextField mapDepthSelectField = new TextField();
    public Button saveMapButton = new Button();
    public ChoiceBox mapChoiceBox = new ChoiceBox();
    public TileEntity selectedTile;
    public Map map;

    @FXML
    public void initialize() throws InterruptedException {
        System.out.println("GameController initialized!");
        mapCreatorPane.setLeft(settingsWindow);
        mapCreatorPane.setCenter(mapWindow);
        mapCreatorPane.setRight(tileWindow);

        mapWindow.setOnMouseClicked(this::mapWindowOnMouseClick);
        mapWindow.setOnMouseDragged(this::mapWindowOnMouseClick);

        mapCreatorPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mapWindow.setWidth(mapCreatorPane.getWidth() * 0.8);
            tileWindow.setPrefWidth(mapCreatorPane.getWidth() * 0.1);
            settingsWindow.setPrefWidth(mapCreatorPane.getWidth() * 0.1);
            tileWindow.getChildren().forEach(node -> {
                Button button = (Button) node;
                button.setPrefWidth(tileWindow.getPrefWidth());
            });

            if(map != null) map.draw(mapWindow.getGraphicsContext2D());
        });
        mapCreatorPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mapWindow.setHeight(mapCreatorPane.getHeight());
            tileWindow.setPrefHeight(mapCreatorPane.getHeight());
            settingsWindow.setPrefHeight(mapCreatorPane.getHeight());;
            tileWindow.getChildren().forEach(node -> {
                Button button = (Button) node;
                button.setPrefHeight(tileWindow.getHeight() / tileWindow.getChildren().stream().count());
            });

            if (map != null) map.draw(mapWindow.getGraphicsContext2D());
        });

        for (TileEntity tile : TileEntity.values()) {
            System.out.println(tile);
            ImageView imageView = new ImageView(new Image(BombermanApplication.class.getResource(tile.getTile().getPathTotexture()).toString()));
            Button button = new Button();
            button.setGraphic(imageView);
            button.setPrefWidth(tileWindow.getPrefWidth());
            button.setPrefHeight(tileWindow.getPrefWidth());
            button.setOnMouseClicked(this::tileOnMouseClick);
            button.setTooltip(new Tooltip(tile.getTile().toString()));
            tileWindow.getChildren().add(button);
        }

        mapNameField.setPromptText("Map name");
        mapNameField.setOnKeyTyped(this::mapNameFieldOnKeyTyped);
        mapWidthField.setPromptText("Map width");
        mapWidthField.setOnKeyTyped(this::mapWidthFieldOnKeyTyped);
        mapHeightField.setPromptText("Map height");
        mapHeightField.setOnKeyTyped(this::mapHeightFieldOnKeyTyped);
        mapDepthField.setPromptText("Map depth (>=2)");
        mapDepthField.setOnKeyTyped(this::mapDepthFieldOnKeyTyped);
        mapDepthSelectField.setPromptText("Map depth select");
        mapDepthSelectField.setOnKeyTyped(this::mapDepthSelectFieldOnKeyTyped);
        saveMapButton.setText("Save map");
        saveMapButton.setOnMouseClicked(this::saveMapButtonOnMouseClick);
        mapChoiceBox.getItems().addAll(BombermanApplication.clientThread.getMapNames());
        mapChoiceBox.setOnMouseClicked(this::mapChoiceBoxOnMouseClick);
        settingsWindow.getChildren().addAll(mapChoiceBox, mapNameField, mapWidthField, mapHeightField, mapDepthField, mapDepthSelectField, saveMapButton);
    }

    private void mapChoiceBoxOnMouseClick(MouseEvent mouseEvent) {
        if (mapChoiceBox.getValue() != null) {
            map = BombermanApplication.clientThread.getMap((String) mapChoiceBox.getValue());
            map.draw(mapWindow.getGraphicsContext2D());
            mapNameField.setText(map.getName());
            mapWidthField.setText(String.valueOf(map.getWidth()));
            mapHeightField.setText(String.valueOf(map.getHeight()));
            mapDepthField.setText(String.valueOf(map.getDepth()));
        }
    }

    private void saveMapButtonOnMouseClick(MouseEvent mouseEvent) {
        try {
            map.setName(mapNameField.getText());
            BombermanApplication.clientThread.saveMap(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapDepthSelectFieldOnKeyTyped(KeyEvent keyEvent) {
    }

    private void mapDepthFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && Integer.parseInt(mapDepthField.getText()) > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()), Integer.parseInt(mapDepthField.getText()));
        }
    }

    private void mapWindowOnMouseClick(MouseEvent mouseEvent) {
        if (selectedTile != null && map != null) {
            int x = (int) (mouseEvent.getX() / (mapWindow.getWidth() / map.getWidth()));
            int y = (int) (mouseEvent.getY() / (mapWindow.getHeight() / map.getHeight()));
            map.setTile(x, y, Integer.parseInt(mapDepthSelectField.getText()), selectedTile.getTile());
            System.out.println("x: " + x + " y: " + y + " z: " + Integer.parseInt(mapDepthSelectField.getText()));
            map.draw(mapWindow.getGraphicsContext2D());
        } else {
            mapWindow.getGraphicsContext2D().setStroke(Color.BLACK);
            mapWindow.getGraphicsContext2D().strokeRect(0, 0, mapWindow.getWidth(), mapWindow.getHeight());
        }
    }

    private void mapHeightFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && Integer.parseInt(mapDepthField.getText()) > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()), Integer.parseInt(mapDepthField.getText()));
        }
    }

    private void mapWidthFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && Integer.parseInt(mapDepthField.getText()) > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()), Integer.parseInt(mapDepthField.getText()));
        }
    }

    private void mapNameFieldOnKeyTyped(KeyEvent keyEvent) {
        System.out.println("Map name changed to " + mapNameField.getText());
        if (map != null) {
            map.setName(mapNameField.getText());
        }
    }

    private void tileOnMouseClick(MouseEvent mouseEvent) {
        selectedTile = TileEntity.values()[tileWindow.getChildren().indexOf(mouseEvent.getSource())];
        System.out.println(selectedTile);
    }

    public void mainMenuButtonOnMouseClick(MouseEvent mouseEvent) throws Exception {
        BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
    }

    public void onKeyPress(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.ESCAPE){
            try {
                BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
