package duplenskikh.simplejetty;

import org.eclipse.jetty.servlet.ServletHandler;

public class Main {
    public static void main(String[] args) {
        try {
            JettyServer jettyServer = new JettyServer();
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
