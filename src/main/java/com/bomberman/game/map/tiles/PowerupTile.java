package com.bomberman.game.map.tiles;

import com.bomberman.game.player.PowerupType;
import javafx.scene.image.Image;

public class PowerupTile extends Tile {
    private PowerupType powerupType;

    public PowerupTile(Image texture, TileType type, boolean isPassable, PowerupType powerupType) {
        super(texture, type, isPassable);
        this.powerupType = powerupType;
    }

    public PowerupType getPowerupType() {
        return powerupType;
    }

    public void setPowerupType(PowerupType powerupType) {
        this.powerupType = powerupType;
    }

    @Override
    public String toString() {
        return "PowerupTile{" +
                "powerupType=" + powerupType +
                ", texture=" + texture +
                ", type=" + type +
                ", isPassable=" + isPassable +
                '}';
    }
}
