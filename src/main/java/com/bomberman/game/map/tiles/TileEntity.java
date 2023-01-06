package com.bomberman.game.map.tiles;

import com.bomberman.BombermanApplication;
import javafx.scene.image.Image;
import com.bomberman.game.player.PowerupType;

public enum TileEntity {
    GRASS(new Tile("images/grass.png", TileType.GROUND, true)),
    BRICK(new DestructibleTile("images/brick.png", TileType.WALL, false, "images/brick_broken.png", 20, 1)),
    BEDROCK(new Tile("images/bedrock.png", TileType.WALL, false)),
    PLAYER(new PlayerTile("images/player.png", TileType.PLAYER, true, "images/player_moving.png", 3, 3)),
    BOMB(new BombTile("images/bomb_explode.png", TileType.BOMB, false, "images/bomb_explode_2.png", 3, 3)),
    EXPLOSION(new Tile("images/flame.png", TileType.EXPLOSION, false)),
    POWERUP_BOMB(new PowerupTile("images/powerup_bomb.png", TileType.POWERUP, true, PowerupType.BOMB_RANGE_UP)),
    //POWERUP_SPEED(new PowerupTile(new Image(BombermanApplication.class.getResource("images/powerup_speed.png").toString()), TileType.POWERUP, true, PowerupType.MOVEMENT_SPEED_UP)),
    //POWERUP_RANGE(new PowerupTile(new Image(BombermanApplication.class.getResource("images/powerup_range.png").toString()), TileType.POWERUP, true, PowerupType.BOMB_COUNT_UP)),
    ;
    private Tile tile;

    TileEntity(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return "TileEntity{" +
                "tile=" + tile +
                '}';
    }
}
