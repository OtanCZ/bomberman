package com.bomberman.game.server.thread;

import com.bomberman.BombermanApplication;
import com.bomberman.controllers.ServerListController;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.game.server.socket.ClientSocket;
import com.bomberman.view.SceneEntity;
import javafx.application.Platform;

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
                        Platform.runLater(() -> {
                            BombermanApplication.gameService.refreshServerList();
                        });
                    }

                    case "Joined" -> {
                        clientSocket.currentServer = (ServerEntity) clientSocket.ois.readObject();
                        System.out.println(clientSocket.currentServer);
                        Platform.runLater(() -> {
                            BombermanApplication.gameService.updateWaitingLobby();
                        });
                    }

                    case "Update" -> {
                        clientSocket.currentServer = (ServerEntity) clientSocket.ois.readObject();
                        System.out.println(clientSocket.currentServer);
                        Platform.runLater(() -> {
                            switch (BombermanApplication.stageManager.getCurrentScene()){
                                case WAITING_LOBBY -> BombermanApplication.gameService.updateWaitingLobby();
                                case GAME -> BombermanApplication.gameService.updateGame();
                                default -> {}
                            }
                        });
                    }

                    case "GameStart" -> {
                        System.out.println("Server started the game!");
                        Platform.runLater(() -> {
                            try {
                                BombermanApplication.stageManager.showScene(SceneEntity.GAME);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    case "GameEnd" -> {
                        Platform.runLater(() -> {
                            try {
                                //TODO: Make end screen scene, this is temporary.
                                BombermanApplication.stageManager.showScene(SceneEntity.MAIN_MENU);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    case "WaitingLobby" -> {
                        Platform.runLater(() -> {
                            try {
                                BombermanApplication.stageManager.showScene(SceneEntity.WAITING_LOBBY);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
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
