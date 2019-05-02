package com.github.mouse0w0.mycef.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static String readString(String name) {
        String result;
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
            Scanner scanner = new Scanner(in, "UTF-8");
            result = scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
