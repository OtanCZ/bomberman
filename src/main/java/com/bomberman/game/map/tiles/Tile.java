package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Tile implements Serializable {
    String pathToTexture;
    TileType type;
    boolean isPassable;

    public Tile(String pathToTexture, TileType type, boolean isPassable) {
        this.pathToTexture = pathToTexture;
        this.type = type;
        this.isPassable = isPassable;
    }

    public String getPathTotexture() {
        return pathToTexture;
    }

    public void setPathTotexture(String pathTotexture) {
        this.pathToTexture = pathTotexture;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "texture=" + pathToTexture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
