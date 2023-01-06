package com.bomberman.game.server.socket;

import com.bomberman.BombermanApplication;
import com.bomberman.game.map.Map;
import com.bomberman.game.player.Player;
import com.bomberman.game.server.ServerEntity;
import com.bomberman.game.server.thread.ClientThread;
import com.bomberman.view.SceneEntity;
import javafx.scene.paint.Color;

import java.io.*;
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
    public List<Map> maps;
    public ClientSocket() throws IOException {
        this.socket = new Socket("localhost", 6969);
        this.servers = new ArrayList<>();
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.maps = loadMaps();

        ClientThread clientThread = new ClientThread(this);
        clientThread.start();
    }

    private List<Map> loadMaps() {
        List<Map> maps = new ArrayList<>();
        try {
            if(BombermanApplication.class.getResource("maps/") != null){
                File defaultMapsFolder = new File(BombermanApplication.class.getResource("maps/").getFile());
                File[] mapsFiles = defaultMapsFolder.listFiles();
                for (File mapFile : mapsFiles) {
                    FileInputStream fileInputStream = new FileInputStream(mapFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    Map map = (Map) objectInputStream.readObject();
                    maps.add(map);
                }
            }

            File customMapsFolder = new File(System.getProperty("user.home") + "/.bomberman/maps/");
            File[] customMapsFiles = customMapsFolder.listFiles();
            for (File customMapFile : customMapsFiles) {
                FileInputStream fileInputStream = new FileInputStream(customMapFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Map map = (Map) objectInputStream.readObject();
                maps.add(map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return maps;
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
        Player pl = new Player("Test", "#000000");
        oos.writeObject(pl);
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

    public List<Map> getMaps() {
        return maps;
    }

    public void saveMap(Map map) throws IOException {
        File mapFile = new File(System.getProperty("user.home") + "/.bomberman/maps/" + map.getName() + ".map");
        System.out.println(mapFile.getAbsolutePath());
        if(!mapFile.getParentFile().exists()) mapFile.getParentFile().mkdirs();
        if(!mapFile.exists()) mapFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(mapFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            oos.writeObject(map);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.maps = loadMaps();
    }

    public Map getMap(String value) {
        for (Map map : maps) {
            if(map.getName().equals(value)) return map;
        }
        return null;
    }

    public List<String> getMapNames() {
        List<String> mapNames = new ArrayList<>();
        for (Map map : maps) {
            mapNames.add(map.getName());
        }
        return mapNames;
    }
}
