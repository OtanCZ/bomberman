package com.bomberman.game.map;

import com.bomberman.game.map.tiles.Tile;

public class Map {
    private Tile[][][] map;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Tile[width][height][];
    }

    public void setTile(int x, int y, int z, Tile tile) {
        map[x][y][z] = tile;
    }

    public Tile getTile(int x, int y, int z) {
        return map[x][y][z];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
