package duplenskikh.crud.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HtmlReader {
    public static String readHtml(String pathname) {
        try {
            StringBuilder response = new StringBuilder("");
            BufferedReader reader = new BufferedReader(new FileReader(new File(pathname)));
            while (reader.ready()) {
                response.append(reader.readLine());
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
