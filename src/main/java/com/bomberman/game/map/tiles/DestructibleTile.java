package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

public class DestructibleTile extends Tile {
    String pathToDestroyedTexture;
    //TileEntity[] destroyRewards;
    int rewardChance;
    int health;

    public DestructibleTile(String pathToTexture, TileType type, boolean isPassable, String pathToDestroyedTexture, /*TileEntity[] destroyRewards,*/ int rewardChance, int health) {
        super(pathToTexture, type, isPassable);
        this.pathToDestroyedTexture = pathToDestroyedTexture;
        //this.destroyRewards = destroyRewards;
        this.rewardChance = rewardChance;
        this.health = health;
    }

    @Override
    public String toString() {
        return "DestructibleTile{" +
                "destroyedTexture=" + pathToDestroyedTexture +
                ", rewardChance=" + rewardChance +
                ", health=" + health +
                ", texture=" + pathToTexture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
