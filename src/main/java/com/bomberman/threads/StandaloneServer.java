package com.bomberman.threads;

import java.io.IOException;

public class StandaloneServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.findBroadcastIp();
    }
}
