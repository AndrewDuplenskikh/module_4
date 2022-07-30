package duplenskikh.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Server {
    public static void start() {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted");
                Router router = new Router(socket.getInputStream(), socket.getOutputStream());
                router.listen();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
