package com.bomberman.game.server.thread;

import com.bomberman.BombermanApplication;
import com.bomberman.controllers.ServerListController;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.game.server.socket.ClientSocket;
import com.bomberman.view.SceneEntity;

import java.io.IOException;

public class ClientThread extends Thread {
    private ClientSocket clientSocket;

    public ClientThread(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run(){
        while(true){
            try {
                String response = (String) clientSocket.ois.readObject();
                System.out.println(response);
                switch (response) {
                    case "Discovered" -> {
                        clientSocket.servers.add((ServerEntity) clientSocket.ois.readObject());
                        System.out.println(clientSocket.servers);
                    }

                    case "Joined" -> {
                        clientSocket.currentServer = (ServerEntity) clientSocket.ois.readObject();
                        System.out.println(clientSocket.currentServer);
                    }

                    case "Update" -> {
                        clientSocket.currentServer = (ServerEntity) clientSocket.ois.readObject();
                        System.out.println(clientSocket.currentServer);
                    }

                    case "Left" -> {
                        clientSocket.currentServer = null;
                    }

                    default -> {
                        System.out.println("Unknown response from server: " + response);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
