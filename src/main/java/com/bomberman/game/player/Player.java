package com.bomberman.game.player;

import com.bomberman.game.map.tiles.PlayerTile;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.net.Socket;

public class Player implements Serializable {
    private String name;
    private String color;
    private PlayerTile tile;
    private int bombs;
    private int maxBombs;
    private int bombRange;
    private int moveSpeed;
    private Socket socket;
    private boolean ready;

    public Player() {

    }
    public Player(String name, String color, PlayerTile tile, int bombs, int maxBombs, int bombRange, int moveSpeed, Socket socket) {
        this.name = name;
        this.color = color;
        this.tile = tile;
        this.bombs = bombs;
        this.maxBombs = maxBombs;
        this.bombRange = bombRange;
        this.moveSpeed = moveSpeed;
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", tile=" + tile +
                ", bombs=" + bombs +
                ", maxBombs=" + maxBombs +
                ", bombRange=" + bombRange +
                ", moveSpeed=" + moveSpeed +
                ", socket=" + socket +
                ", ready=" + ready +
                '}';
    }

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.tile = null;
        this.bombs = 0;
        this.maxBombs = 1;
        this.bombRange = 1;
        this.moveSpeed = 10;
        this.ready = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PlayerTile getTile() {
        return tile;
    }

    public void setTile(PlayerTile tile) {
        this.tile = tile;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int getMaxBombs() {
        return maxBombs;
    }

    public void setMaxBombs(int maxBombs) {
        this.maxBombs = maxBombs;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
