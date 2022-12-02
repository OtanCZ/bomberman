package com.bomberman.game.map.tiles;

public enum TileEntity {
    GRASS(new Tile("images/grass.png", TileType.GROUND, true)),
    BRICK(new DestructibleTile("images/brick.png", TileType.WALL, false, "images/brick_destroyed.png", 20, 1)),
    BEDROCK(new Tile("images/bedrock.png", TileType.WALL, false));
    private Tile tile;

    TileEntity(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }
}
