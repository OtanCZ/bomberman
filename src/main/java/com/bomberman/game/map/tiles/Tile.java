package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Tile implements Serializable {
    String pathToTexture;
    TileType type;
    boolean isPassable;
    int x;
    int y;
    int z;

    public Tile(String pathToTexture, TileType type, boolean isPassable) {
        this.pathToTexture = pathToTexture;
        this.type = type;
        this.isPassable = isPassable;
    }

    public Tile(String pathToTexture, TileType type, boolean isPassable, int x, int y, int z) {
        this.pathToTexture = pathToTexture;
        this.type = type;
        this.isPassable = isPassable;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getPathToTexture() {
        return pathToTexture;
    }

    public void setPathToTexture(String pathToTexture) {
        this.pathToTexture = pathToTexture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
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
