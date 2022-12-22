package com.bomberman.game.map.tiles;

import com.bomberman.BombermanApplication;
import javafx.scene.image.Image;
import com.bomberman.game.player.PowerupType;

public enum TileEntity {
    GRASS(new Tile(new Image(BombermanApplication.class.getResource("images/grass.png").toString()), TileType.GROUND, true)),
    BRICK(new DestructibleTile(new Image(BombermanApplication.class.getResource("images/brick.png").toString()), TileType.WALL, false, new Image(BombermanApplication.class.getResource("images/brick_broken.png").toString()), 20, 1)),
    BEDROCK(new Tile(new Image(BombermanApplication.class.getResource("images/bedrock.png").toString()), TileType.WALL, false)),
    PLAYER(new PlayerTile(new Image(BombermanApplication.class.getResource("images/player.png").toString()), TileType.PLAYER, true, new Image(BombermanApplication.class.getResource("images/player_moving.png").toString()), 3, 3)),
    BOMB(new BombTile(new Image(BombermanApplication.class.getResource("images/bomb_explode.png").toString()), TileType.BOMB, false, new Image(BombermanApplication.class.getResource("images/bomb_explode_2.png").toString()), 3, 3)),
    EXPLOSION(new Tile(new Image(BombermanApplication.class.getResource("images/flame.png").toString()), TileType.EXPLOSION, false)),
    POWERUP_BOMB(new PowerupTile(new Image(BombermanApplication.class.getResource("images/powerup_bomb.png").toString()), TileType.POWERUP, true, PowerupType.BOMB_RANGE_UP)),
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
}
