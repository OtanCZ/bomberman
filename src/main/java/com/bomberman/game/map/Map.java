package com.bomberman.game.map;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.tiles.Tile;
import com.bomberman.game.map.tiles.TileEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Arrays;

public class Map implements Serializable {
    private TileEntity[][][] map;
    private int width;
    private int height;
    private int depth;
    private String name;

    public Map(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        map = new TileEntity[width][height][depth];
    }

    public Map(String name, int width, int height, int depth){
        this.name = name;
        this.width = width;
        this.height = height;
        map = new TileEntity[width][height][depth];
    }
    public Map(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        map = new TileEntity[width][height][1];
    }

    public Map(String name, TileEntity[][][] map){
        this.name = name;
        this.map = map;
    }

    public void setTile(int x, int y, int z, TileEntity tile) {
        map[x][y][z] = tile;
    }

    public TileEntity getTile(int x, int y, int z) {
        return map[x][y][z];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileEntity[][][] getMap() {
        return map;
    }

    public void setMap(TileEntity[][][] map) {
        this.map = map;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void draw(GraphicsContext graphicsContext2D) {
        graphicsContext2D.clearRect(0, 0, graphicsContext2D.getCanvas().getWidth(), graphicsContext2D.getCanvas().getHeight());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for(int z = 0; z < depth; z++) {
                    if (map[x][y][z] != null) {
                        Tile tile = map[x][y][z].getTile();
                        graphicsContext2D.drawImage(new Image(BombermanApplication.class.getResource(tile.getPathTotexture()).toString()), x * (graphicsContext2D.getCanvas().getWidth() / width), y * (graphicsContext2D.getCanvas().getHeight() / height), graphicsContext2D.getCanvas().getWidth() / width, graphicsContext2D.getCanvas().getHeight() / height);
                    }
                }
            }
        }
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "Map{" +
                "map=" + Arrays.toString(map) +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", name='" + name + '\'' +
                '}';
    }
}
