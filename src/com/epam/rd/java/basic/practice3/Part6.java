package com.epam.rd.java.basic.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Part6 {

    /**
     * String input get part1.txt
     */
    private static final String ENCODING = "Cp1251";

    public static String convert(String s) {

        Pattern p = Pattern.compile("(\\b\\w+\\b)(?=[\\s\\S]*\\b\\1\\b[\\s\\S]*\\b\\1\\b)",
                Pattern.UNICODE_CHARACTER_CLASS);
        String res = p.matcher(s + " " + s).replaceAll("_$1");
        res = res.substring(0, res.length() - 1 - p.matcher(s).replaceAll("_$1").length());

        return res;
    }

    public static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Main function.
     *
     * @param args - argument functions main.
     * @throws Exception
     * @throws IOException
     */
    public static void main(String[] args) {
        System.out.println(Part6.convert(readFile("part6.txt")));

    }

}
