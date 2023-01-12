package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

public class PlayerTile extends Tile {
    String pathToMovingTexture;
    int health;
    int maxHealth;
    boolean taken;

    public PlayerTile(String texture, TileType type, boolean isPassable, String pathToMovingTexture, int health, int maxHealth) {
        super(texture, type, isPassable);
        this.pathToMovingTexture = pathToMovingTexture;
        this.health = health;
        this.maxHealth = maxHealth;
        this.taken = false;
    }

    public String getMovingTexture() {
        return pathToMovingTexture;
    }

    public void setMovingTexture(String pathToMovingTexture) {
        this.pathToMovingTexture = pathToMovingTexture;
    }

    public String getPathToMovingTexture() {
        return pathToMovingTexture;
    }

    public void setPathToMovingTexture(String pathToMovingTexture) {
        this.pathToMovingTexture = pathToMovingTexture;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
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
                "movingTexture=" + pathToMovingTexture +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", texture=" + pathToTexture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
