package com.bomberman.game.map.tiles;

import javafx.scene.image.Image;

public class Tile {
    Image texture;
    TileType type;
    boolean isPassable;

    public Tile(Image texture, TileType type, boolean isPassable) {
        this.texture = texture;
        this.type = type;
        this.isPassable = isPassable;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
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
}
