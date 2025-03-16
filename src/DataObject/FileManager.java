/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukass
 */
public class FileManager {

    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readDataFromFile() throws IOException, Exception {
        List<String> result = new ArrayList<>();
        try {
            File fileData = new File(fileName);
            if (fileData.length() > 0) {
                result = Files.readAllLines(fileData.toPath(), Charset.forName("utf-8"));
            }
        } catch (Exception e) {
            throw new Exception(">>CAN NOT READ DATA");
        }
        return result;
    }

    public void writeDataToFile(String data) throws IOException, Exception {
        try (PrintWriter writer = new PrintWriter(fileName, "utf-8")) {
            writer.print(data);
        } catch (Exception e) {
            throw new Exception(">>CAN NOT WRITE DATA");
        }
    }

}
