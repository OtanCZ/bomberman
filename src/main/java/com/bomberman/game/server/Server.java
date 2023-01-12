package com.bomberman.game.server;

import com.bomberman.game.map.Map;
import com.bomberman.game.map.tiles.PlayerTile;
import com.bomberman.game.map.tiles.TileEntity;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.thread.ClientAcceptThread;
import com.bomberman.game.server.thread.ServerThread;
import com.bomberman.game.service.GameService;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public ServerSocket socket;
    public HashMap<String, Player> players;
    public String name;
    public String ip;
    public InetAddress broadcast;
    public int port;
    public HashMap<String, ServerThread> clients;
    public int maxPlayers;
    public Map map;
    public String version;
    public GameState gameState;

    public Server(int port) throws IOException {
        this.port = port;
        this.name = "Bomberman Server";
        this.ip = "localhost";
        this.socket = new ServerSocket(port);
        this.clients = new HashMap<>();
        this.players = new HashMap<>();
        this.gameState = GameState.LOBBY;
        this.map = GameService.loadMaps().get(0);
        this.version = "0.0.1";
        this.maxPlayers = 4;
    }

    public void start() throws InterruptedException {
        ClientAcceptThread clientAcceptThread = new ClientAcceptThread(this);
        clientAcceptThread.start();

        while(true){
            while(gameState == GameState.LOBBY){
                if(allPlayersReady()){
                    gameState = GameState.INGAME;
                }
                Thread.sleep(1000);
            }

            System.out.println("All players ready, switching to game.");
            sendToAllClients("GameStart");
            updateAllClients();

            while (gameState == GameState.INGAME){
                for (Player player : players.values()) {
                    player.setTile(map.findNotTakenPlayerTile());
                }

                boolean clean = false;
                while (!clean){
                    PlayerTile tile = map.findNotTakenPlayerTile();
                    System.out.println(tile);
                    if(map.findNotTakenPlayerTile() != null){
                        map.deleteTile(tile);
                    } else {
                        clean = true;
                    }
                }


            }

            System.out.println("Game ended.");
            sendToAllClients("GameEnd");

            while(gameState == GameState.ENDGAME) {
                Thread.sleep(5000);
                gameState = GameState.LOBBY;
            }

            for(Player p : players.values()){
                p.setReady(false);
            }

            System.out.println("Waiting lobby.");
            sendToAllClients("WaitingLobby");
            updateAllClients();
        }
    }

    public boolean allPlayersReady(){
        int playersReady = 0;
        for(Player player : players.values()) {
            if(player.isReady()){
                playersReady++;
            }
        }
        if(playersReady >= players.size() && playersReady >= 1){
            return true;
        } else {
            return false;
        }
    }
    public void sendToAllClients(Object object) {
        for(ServerThread st : clients.values()){
            st.send(object);
        }
    }

    public void updateAllClients() {
        ServerEntity se = new ServerEntity(this.name, this.ip, this.port, this.players.values().stream().toList(), this.gameState, this.maxPlayers, this.map, this.version);

        for(ServerThread st : clients.values()){
                st.send("Update");
                st.send(se);
        }
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

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
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