package com.bomberman.game.server;

import com.bomberman.game.map.Map;
import com.bomberman.game.player.Player;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ServerEntity implements Serializable {

    public String name;
    public String ip;
    public int port;
    public Collection<Player> players;
    public GameState gameState;
    public int maxPlayers;
    public Map map;
    public String version;

    public ServerEntity() {}
    public ServerEntity(String name, String ip, int port, Collection<Player> players, GameState gameState, int maxPlayers, Map map, String version) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.players = players;
        this.gameState = gameState;
        this.maxPlayers = maxPlayers;
        this.map = map;
        this.version = version;
    }

    @Override
    public String toString() {
        return "ServerEntity{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", players=" + players +
                ", gameState=" + gameState +
                ", maxPlayers=" + maxPlayers +
                ", map=" + map +
                ", version='" + version + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}