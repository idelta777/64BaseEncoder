package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Encoder64 {

    private static final char[] BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static String encode(byte[] bytes) {
        String encodedData = "";
        String allBytes = "";

        // We convert the byte array into a string representing the binary value
        // Each byte is right padded to 8 digits with 0s
        // Must use bitwise AND operator since byte is signed, and we want an unsigned int
        for(byte b : bytes) {
            allBytes += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }

        // Missing bytes calculates how many equal signs we'll need to pad at the end
        int missingBytes = allBytes.length() % 24 == 0 ? 0 : 24 - (allBytes.length() - 24*(int)(allBytes.length() / 24));
        int numberOfEquals = missingBytes / 8;

        // But in the original bit array we only need to right pad so we get a multiple of 6, not 24
        int missingChunk = allBytes.length() % 6 == 0 ? 0 : 6 - (allBytes.length() - 6*(int)(allBytes.length() / 6));
        for(int i=0; i<missingChunk; i++) {
            allBytes += "0";
        }

        // We go through 6 bit chunks and getting the related Base64 char
        for(int i=0; i<allBytes.length(); i += 6) {
            String chunk = allBytes.substring(i, i+6);
            encodedData += BASE64_CHARS[Integer.parseInt(chunk, 2)];
        }

        // We add the necessary = chars to get a multiple of 24
        for(int i=0; i<numberOfEquals; i++) {
            encodedData += "=";
        }

        return encodedData;
    }

    private char getBase64Char(int decimal) {
        return BASE64_CHARS[decimal];
    }
}
