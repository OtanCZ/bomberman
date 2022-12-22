package com.bomberman.game.server;

import java.io.IOException;

public class StandaloneServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(6969);
        server.start();
    }
}
