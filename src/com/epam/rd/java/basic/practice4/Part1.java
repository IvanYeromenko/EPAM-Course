package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {


    private static final String FILE_NAME = "part1.txt";

    private static final String ENCODING = "Cp1251";

    private static final String LS = System.lineSeparator();

    private static final int WORD_MAX_LENGTH = 3;

    public static boolean flag;

    private Part1() {
    }

    public static String convert(String input) {
        String pre;
        String post;
        StringBuffer word;
        final StringBuffer out = new StringBuffer();
        final String letter = "a-zA-Z\\p{InCyrillic}";
        final String regex = "(^|[^" + letter + "]*)([" + letter + "]+)([^" + letter + "]*|$)";

        final Matcher m = Pattern.compile(regex).matcher(input);
        while (m.find()) {
            pre = m.group(1);
            word = new StringBuffer(m.group(2));
            post = m.group(3);
            if (word.length() > WORD_MAX_LENGTH) {
                word = invertCase(word);
            }
            m.appendReplacement(out, pre + word.toString() + post);
        }
        m.appendTail(out);

        String outString = out.toString();
        if (outString.endsWith(LS)) {
            outString = outString.trim();
        }

        return outString;
    }

    private static StringBuffer invertCase(StringBuffer text) {

        final StringBuffer sb = new StringBuffer();
        final String temp = text.toString();
        final char[] chars = temp.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
                sb.append(chars[i]);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
                sb.append(chars[i]);
            }
        }

        return sb;
    }

    public static void main(String[] args){
        try {
            System.out.println(Part1.run(FILE_NAME, ENCODING));
        } catch (FileNotFoundException ex){
            //
        }
    }

    public static String readFile(final String path, final String encoding) throws FileNotFoundException{
        final StringBuilder builder = new StringBuilder();
        try (Scanner scan = new Scanner(new FileInputStream(new File(path)), encoding)) {
            while (scan.hasNextLine()) {
                builder.append(scan.nextLine()).append(System.lineSeparator());
            }
            if (flag) {
                throw new IllegalArgumentException();
            }
        }

        return builder.toString().trim();
    }

    public static String run(final String path, final String encoding) throws FileNotFoundException{
        final String res = readFile(path, encoding);
        return convert(res);
    }
}
