package duplenskikh.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HtmlReader {
    public static String readHtml(String pathname) {
        StringBuilder response = new StringBuilder("");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(pathname)));
            while (reader.ready()) {
                response.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
