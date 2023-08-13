package org.example;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        String imagePath = "/Users/manuelramirez/Downloads/cbimage.png"; // Replace with the path to your image file

        try {
            byte[] imageBytes = readImageBytes(imagePath);

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();

            clipboard.setContents(new StringSelection(Encoder64.encode(imageBytes)), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readImageBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);

        // Using java.io.FileInputStream
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            byte[] imageBytes = new byte[(int) fileInputStream.available()];
            fileInputStream.read(imageBytes);
            return imageBytes;
        }

        // Or using java.nio.file.Files
        // byte[] imageBytes = Files.readAllBytes(path);
        // return imageBytes;
    }
}