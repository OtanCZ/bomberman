package com.bomberman.controllers;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.Map;
import com.bomberman.game.map.tiles.Tile;
import com.bomberman.game.map.tiles.TileEntity;
import com.bomberman.game.server.GameState;
import com.bomberman.view.SceneEntity;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MapCreatorController {
    public BorderPane mapCreatorPane;
    public VBox settingsWindow = new VBox();
    public Canvas mapWindow = new Canvas();
    public VBox tileWindow = new VBox();
    public TextField mapNameField = new TextField();
    public TextField mapWidthField = new TextField();
    public TextField mapHeightField = new TextField();
    public TextField mapDepthField = new TextField();
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
            ImageView imageView = new ImageView(tile.getTile().getTexture());
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
        settingsWindow.getChildren().addAll(mapNameField, mapWidthField, mapHeightField);
    }

    private void mapDepthFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && mapDepthField.getText().length() > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()));
        }
    }

    private void mapWindowOnMouseClick(MouseEvent mouseEvent) {
        if (selectedTile != null && map != null) {
            int x = (int) (mouseEvent.getX() / (mapWindow.getWidth() / map.getWidth()));
            int y = (int) (mouseEvent.getY() / (mapWindow.getHeight() / map.getHeight()));
            map.setTile(x, y, 0, selectedTile);
            map.draw(mapWindow.getGraphicsContext2D());
        } else {
            mapWindow.getGraphicsContext2D().setStroke(Color.BLACK);
            mapWindow.getGraphicsContext2D().strokeRect(0, 0, mapWindow.getWidth(), mapWindow.getHeight());
        }
    }

    private void mapHeightFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && mapDepthField.getText().length() > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()));
        }
    }

    private void mapWidthFieldOnKeyTyped(KeyEvent keyEvent) {
        if (mapWidthField.getText().length() > 0 && mapHeightField.getText().length() > 0 && mapDepthField.getText().length() > 2) {
            map = new Map(Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()));
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
    }
}
