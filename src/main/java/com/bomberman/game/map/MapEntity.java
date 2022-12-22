package com.bomberman.game.map;


import com.bomberman.game.map.tiles.TileEntity;

public enum MapEntity {
    MAP1(new Map("map1", 10, 10));

    private Map map;

    MapEntity(Map map1) {
        this.map = map1;
    }
}
