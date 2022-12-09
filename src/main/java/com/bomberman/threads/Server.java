package com.bomberman.threads;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Server {
    public MulticastSocket socket;
    public String name;
    public String ip;
    public String broadcastIp;
    public int port;
    public int players;
    public int maxPlayers;
    public String map;
    public String version;

    public Server(String name, String ip, int port, int players, int maxPlayers, String map, String version) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.map = map;
        this.version = version;
    }

    public Server() {

    }

    public Pane getPane(){
        Pane pane = new Pane();
        pane.getChildren().add(new Label(this.toString()));
        return pane;
    }

    public void start() throws IOException {
        socket = new MulticastSocket(port);
    }

    public void findBroadcastIp() throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (((Enumeration<?>) interfaces).hasMoreElements())
        {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback())
                continue;
            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses())
            {
                InetAddress broadcast = interfaceAddress.getBroadcast();
                if (broadcast == null)
                    continue;

                System.out.println(broadcast.getHostAddress());
            }
        }
    }

    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", players=" + players +
                ", maxPlayers=" + maxPlayers +
                ", map='" + map + '\'' +
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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}