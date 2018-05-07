package main;


import servers.SimpleServer;
import servers.ThreadPooledServer;
import servlets.TesterServlet;

import javax.annotation.Resource;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {

//        ThreadPooledServer server = new ThreadPooledServer(8080);
        SimpleServer server  = new SimpleServer();

        server.start();
        Logger.getGlobal().info("Server started");

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    /*    server.stop();
        Logger.getGlobal().info("Server stopped");*/
    }

}
