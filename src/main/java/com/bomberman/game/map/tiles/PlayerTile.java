package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

public class PlayerTile extends Tile {
    Image movingTexture;
    int health;
    int maxHealth;

    public PlayerTile(Image texture, TileType type, boolean isPassable, Image movingTexture, int health, int maxHealth) {
        super(texture, type, isPassable);
        this.movingTexture = movingTexture;
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public Image getMovingTexture() {
        return movingTexture;
    }

    public void setMovingTexture(Image movingTexture) {
        this.movingTexture = movingTexture;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        return "PlayerTile{" +
                "movingTexture=" + movingTexture +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", texture=" + texture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
