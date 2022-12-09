package com.bomberman.game.map.tiles;

import com.bomberman.BombermanApplication;
import javafx.scene.image.Image;

public enum TileEntity {
    GRASS(new Tile(new Image("file:images/grass.png"), TileType.GROUND, true)),
    BRICK(new DestructibleTile(new Image(BombermanApplication.class.getResource("images/brick.png").toString()), TileType.WALL, false, new Image("file:images/brick_broken.png"), 20, 1)),
    BEDROCK(new Tile(new Image("file:images/bedrock.png"), TileType.WALL, false));
    private Tile tile;

    TileEntity(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }
}
