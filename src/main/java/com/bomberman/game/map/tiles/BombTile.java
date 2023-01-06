package com.bomberman.game.map.tiles;
import javafx.scene.image.Image;

public class BombTile extends Tile {
    String pathToExpodingTexture;
    int explosionRange;
    int explosionTime;

    public BombTile(String texture, TileType type, boolean isPassable, String pathToExpodingTexture, int explosionRange, int explosionTime) {
        super(texture, type, isPassable);
        this.pathToExpodingTexture = pathToExpodingTexture;
        this.explosionRange = explosionRange;
        this.explosionTime = explosionTime;
    }

    public String getExpodingTexture() {
        return pathToExpodingTexture;
    }

    public void setExpodingTexture(String pathToExpodingTexture) {
        this.pathToExpodingTexture = pathToExpodingTexture;
    }

    public int getExplosionRange() {
        return explosionRange;
    }

    public void setExplosionRange(int explosionRange) {
        this.explosionRange = explosionRange;
    }

    public int getExplosionTime() {
        return explosionTime;
    }

    public void setExplosionTime(int explosionTime) {
        this.explosionTime = explosionTime;
    }

    @Override
    public String toString() {
        return "BombTile{" +
                "expodingTexture=" + pathToExpodingTexture +
                ", explosionRange=" + explosionRange +
                ", explosionTime=" + explosionTime +
                ", texture=" + pathToTexture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
