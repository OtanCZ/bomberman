package com.bomberman.game.server.thread;

import com.bomberman.game.player.Player;
import com.bomberman.game.server.Server;
import com.bomberman.game.server.ServerEntity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    public Socket client;
    public Server server;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;

    public ServerThread(Socket client, Server server) throws IOException {
        this.client = client;
        this.server = server;
        this.ois = new ObjectInputStream(client.getInputStream());
        this.oos = new ObjectOutputStream(client.getOutputStream());
    }
    public void run() {
        System.out.println("Client connected");
        while(true){
            try {
                String request = (String) ois.readObject();

                switch (request) {
                    case "Discover" -> {
                        System.out.println("Sending server info to client " + client.getInetAddress().toString());
                        oos.writeObject("Discovered");
                        oos.writeObject(new ServerEntity(server.name, server.ip, server.port, server.players, server.clients.values().stream().toList(), server.gameState, server.maxPlayers, server.map, server.version));
                        oos.flush();
                    }

                    case "Join" -> {
                        String name = (String) ois.readObject();
                        Player player = new Player(name, "#000000");
                        System.out.println("Client " + client.getInetAddress().toString() + " (" + player.getName() + ") " + "joined server.");
                        server.clients.put(client.getInetAddress().toString(), player);
                        server.players = server.clients.size();
                        oos.writeObject("Joined");
                        System.out.println(server);
                        ServerEntity serverEntity = new ServerEntity(server.name, server.ip, server.port, server.players, server.clients.values().stream().toList(), server.gameState, server.maxPlayers, server.map, server.version);
                        System.out.println(serverEntity);
                        oos.writeObject(serverEntity);
                        oos.flush();
                    }

                    case "Ready" -> {
                        if(server.clients.get(client.getInetAddress().toString()).isReady()){
                            System.out.println("Client " + client.getInetAddress().toString() + " is not ready.");
                            server.clients.get(client.getInetAddress().toString()).setReady(false);
                        } else {
                            server.clients.get(client.getInetAddress().toString()).setReady(true);
                            System.out.println("Client " + client.getInetAddress().toString() + " is ready.");
                        }
                        oos.writeObject("Update");
                        ServerEntity se = new ServerEntity(server.name, server.ip, server.port, server.players, server.clients.values().stream().toList(), server.gameState, server.maxPlayers, server.map, server.version);
                        oos.writeObject(se);
                        System.out.println(server);
                        System.out.println(se);
                        oos.flush();
                    }

                    case "Leave" -> {
                        System.out.println("Client " + client.getInetAddress().toString() + " left server.");
                        oos.writeObject("Left");
                    }

                    default -> System.out.println("Unknown request: " + request);
                }
                oos.reset();
            } catch (IOException e) {
                System.out.println("Client " + client.getInetAddress().toString() + " probably disconnected.");
                server.clients.remove(client.getInetAddress().toString());
                server.players = server.clients.size();
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "ServerThread{" +
                "client=" + client +
                ", server=" + server +
                '}';
    }
}
