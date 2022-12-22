package com.bomberman.game.map.tiles;
import javafx.scene.image.Image;

public class BombTile extends Tile {
    Image expodingTexture;
    int explosionRange;
    int explosionTime;

    public BombTile(Image texture, TileType type, boolean isPassable, Image expodingTexture, int explosionRange, int explosionTime) {
        super(texture, type, isPassable);
        this.expodingTexture = expodingTexture;
        this.explosionRange = explosionRange;
        this.explosionTime = explosionTime;
    }

    public Image getExpodingTexture() {
        return expodingTexture;
    }

    public void setExpodingTexture(Image expodingTexture) {
        this.expodingTexture = expodingTexture;
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
                "expodingTexture=" + expodingTexture +
                ", explosionRange=" + explosionRange +
                ", explosionTime=" + explosionTime +
                ", texture=" + texture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
