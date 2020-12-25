package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part6 {

    public static boolean flag;

    private static final String LS = System.lineSeparator();

    public static final String ENCODING = "Cp1251";

    private static final String fileName = "part6.txt";

    /*public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Part6.fileName = fileName;
    }*/

    public void run() {
        String res = readFile(fileName, ENCODING);
        input(res);
    }

    public static String readFile(final String path, final String encoding) {
        final StringBuilder builder = new StringBuilder();
        try (Scanner scan = new Scanner(new FileInputStream(new File(path)), encoding)) {
            while (scan.hasNextLine()) {
                builder.append(scan.nextLine()).append(System.lineSeparator());
            }
            if (flag) {
                throw new IllegalArgumentException();
            }
        } catch (FileNotFoundException ex){
            //
        }
        return builder.toString();
    }

    public void input(String res) {
        res = res.replaceAll("[^A-Za-z\\p{InCyrillic}]", " ");
        final String letter = "[A-Za-z]";
        final String startStr = "(^|\\s)([" + letter + "]";
        final String endStr = ")(?=\\s|" + LS + "|$)";

        final String letterCyr = "\\p{InCyrillic}.*?\\-\\.";
        final String startStrCyr = "(^|\\s)([" + letterCyr + "]";
        final String endStrCyr = ")(?=\\s|" + LS + "|$)";

        Scanner sc = new Scanner(System.in, ENCODING);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            switch (str) {
                case "latn":
                    latn(res, startStr, endStr, str);
                    break;
                case "Latn":
                    latn(res, startStr, endStr, str);
                    break;
                case "cyrl":
                    cyrl(res, startStrCyr, endStrCyr, str);
                    break;

                case "Cyrl":
                    cyrl(res, startStrCyr, endStrCyr, str);
                    break;

                case "stop":
                    break;

                case "Stop":
                    break;

                default:
                    System.out.println(str + ": Incorrect input");
            }
        }
    }

    private void cyrl(String res, final String startStrCyr, final String endStrCyr, String str) {
        String regex;
        regex = startStrCyr + "{1,}" + endStrCyr;
        System.out.println(str + ": " + getResTypeParse(regex, res));
    }

    private void latn(String res, final String startStr, final String endStr, String str) {
        String regex;
        regex = startStr + "{0,}" + endStr;
        System.out.println(str + ": " + getResTypeParse(regex, res));
    }

    public String getResTypeParse(final String regex, String input) {
        String word;
        StringBuilder out = new StringBuilder();
        Matcher m = Pattern.compile(regex).matcher(input);
        boolean found = false;
        while (m.find()) {
            word = m.group(2);
            if (!found) {
                out.append(word);
                found = true;
            } else {
                out.append(" ".concat(word));
            }
        }
        if (!found) {
            out.append("No such values");
        }

        return out.toString().trim();
    }

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        part6.run();

    }

}
