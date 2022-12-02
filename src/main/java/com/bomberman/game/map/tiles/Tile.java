package com.bomberman.game.map.tiles;

public class Tile {
    String pathToTexture;
    TileType type;
    boolean isPassable;

    public Tile(String pathToTexture, TileType type, boolean isPassable) {
        this.pathToTexture = pathToTexture;
        this.type = type;
        this.isPassable = isPassable;
    }
}
