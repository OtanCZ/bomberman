package com.bomberman.game.server.socket;

import com.bomberman.BombermanApplication;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.game.server.thread.ClientThread;
import com.bomberman.view.SceneEntity;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket {
    public List<ServerEntity> servers;
    public ServerEntity currentServer;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public ClientSocket() throws IOException {
        this.socket = new Socket("localhost", 6969);
        this.servers = new ArrayList<>();
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());

        ClientThread clientThread = new ClientThread(this);
        clientThread.start();
    }

    public ClientSocket(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.servers = new ArrayList<>();
    }

    public void discoverServers() throws IOException {
        System.out.println("Sending discovery request.");
        servers.clear();
        oos.writeObject("Discover");
        oos.flush();
    }

    public void connectToServer(String ip, int port) throws Exception {
        System.out.println("Sending join request to " + ip + ":" + port + ".");
        oos.writeObject("Join");
        oos.writeObject(new Player("Test", "#000000"));
        oos.flush();
    }

    public List<ServerEntity> getServers() {
        return servers;
    }

    public void setServers(List<ServerEntity> servers) {
        this.servers = servers;
    }

    public void readyUp() {
        if(currentServer != null){
            try {
                oos.writeObject("Ready");
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void leaveServer() {
        if(currentServer != null){
            try {
                oos.writeObject("Leave");
                oos.flush();
                Thread.sleep(100);
                if(currentServer == null){
                    System.out.println("Left server.");
                    BombermanApplication.clientThread.currentServer = null;
                    BombermanApplication.stageManager.showScene(SceneEntity.SERVER_LIST);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
