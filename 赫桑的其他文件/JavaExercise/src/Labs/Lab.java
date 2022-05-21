package Labs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Lab {
    public static void main(String[] args) throws IOException {
        try {
            String data = " This content will append to the end of the file";

            File file = new File("javaio-appendfile.txt");

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            fileWritter.write(data);
            fileWritter.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
