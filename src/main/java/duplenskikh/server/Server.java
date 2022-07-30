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
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream());

                String inputData = input.readLine();
                String[] arr = inputData.split(" ");
                String method = arr[0];
                String route = arr[1];
                System.out.println(method);
                System.out.println(route);

                if (route.equals("/qwe")) {
                    String response = readHtml("index.html");
                    response(response, output);
                }

                if (route.equals("/favicon.ico")) {
                    byte[] bytes = Files.readAllBytes(Paths.get("favicon.ico"));
                    Integer length = bytes.length;
                    System.out.println(bytes.length);

                    File icon = new File("favicon.ico");
                    OutputStream os = socket.getOutputStream();
                    os.write(bytes);
                    os.flush();
                    os.close();
//                    output.println("HTTP/1.1 200 OK");
//                    output.println("Content-Type: image/png");
//                    output.println("Content-Length: ".concat(length.toString()));
//                    output.println();
//                    output.println();
//                    output.flush();
//                    output.close();
                }

//                while (input.ready()) {
//                    String nextLine = input.readLine();
//                    if (nextLine.matches("Host:(.*)")) {
//                        System.out.println("ZALYPA");
//                    }
//                    System.out.println(nextLine);
//                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void response(String response, PrintWriter output) {
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println(response);
        output.flush();
        output.close();
    }

    private static String readHtml(String pathname) {
        String response = "";
        try {
            BufferedReader htmlReader = new BufferedReader(new FileReader(new File(pathname)));
            while (htmlReader.ready()) {
                response = response.concat(htmlReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
