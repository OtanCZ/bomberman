package com.bomberman.game.server;

import com.bomberman.game.map.Map;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.thread.ClientAcceptThread;
import com.bomberman.game.server.thread.ServerThread;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public ServerSocket socket;
    public ConcurrentHashMap<String, Player> clients;
    public String name;
    public String ip;
    public InetAddress broadcast;
    public int port;
    public int players;
    public int maxPlayers;
    public Map map;
    public String version;
    public GameState gameState;

    public Server(ServerSocket socket, ConcurrentHashMap<String, Player> clients, String name, String ip, InetAddress broadcast, int port, int players, int maxPlayers, Map map, String version) {
        this.socket = socket;
        this.clients = clients;
        this.name = name;
        this.ip = ip;
        this.broadcast = broadcast;
        this.port = port;
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.map = map;
        this.version = version;
    }

    public Server(int port) throws IOException {
        this.port = port;
        this.name = "Bomberman Server";
        this.ip = "localhost";
        this.socket = new ServerSocket(port);
        this.clients = new ConcurrentHashMap<>();
        this.players = this.clients.size();
        this.gameState = GameState.LOBBY;
        this.map = new Map(10, 10);
        this.version = "0.0.1";
        this.maxPlayers = 4;
    }

    public void start() {
        ClientAcceptThread clientAcceptThread = new ClientAcceptThread(this);
        clientAcceptThread.start();

        while(true){
            while(gameState == GameState.LOBBY) {
                int playersReady = 0;
                for(Player player : clients.values()) {
                    if(player.getReady()){
                        playersReady++;
                    }
                }
                if(playersReady >= players && playersReady >= 1){
                    System.out.println("All players ready, switching to game.");
                    gameState = GameState.INGAME;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
    public void updateAllClients() {
        for(String c : clients.keySet()){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(clients.get(c).getOutputStream());
                oos.writeObject(new ServerEntity(this.name, this.ip, this.port, this.players, this.maxPlayers, this.map, this.version));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     */

    public void startDiscoverServer() throws IOException {
        ServerSocket socket = new ServerSocket();
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

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
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

    @Override
    public String toString() {
        return "Server{" +
                "socket=" + socket +
                ", clients=" + clients +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", broadcast=" + broadcast +
                ", port=" + port +
                ", players=" + players +
                ", maxPlayers=" + maxPlayers +
                ", map=" + map +
                ", version='" + version + '\'' +
                ", gameState=" + gameState +
                '}';
    }
}