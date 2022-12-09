package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

public class DestructibleTile extends Tile {
    Image destroyedTexture;
    //TileEntity[] destroyRewards;
    int rewardChance;
    int health;

    public DestructibleTile(Image texture, TileType type, boolean isPassable, Image destroyedTexture, /*TileEntity[] destroyRewards,*/ int rewardChance, int health) {
        super(texture, type, isPassable);
        this.destroyedTexture = destroyedTexture;
        //this.destroyRewards = destroyRewards;
        this.rewardChance = rewardChance;
        this.health = health;
    }
}
