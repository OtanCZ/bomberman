package com.bomberman.game.server.thread;

import com.bomberman.game.server.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientAcceptThread extends Thread {
    private Server server;

    public ClientAcceptThread(Server server) {
        this.server = server;
    }

    public void run() {
        while(true){
            try {
                Socket client = server.socket.accept();
                ServerThread serverThread = new ServerThread(client, this.server);
                serverThread.run();
                server.clients.put(client.getInetAddress().toString(), client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
