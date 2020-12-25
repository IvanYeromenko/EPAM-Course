package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {

    private Part1() {
        throw new IllegalStateException("Utility class");
    }

    public static String convert1(String input) {

        String[] text = input.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < text.length; i++) {
            String[] a = text[i].split(";");
            sb.append(a[0] + ": " + a[2] + System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        String[] text = input.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < text.length; i++) {
            String[] a = text[i].split("[\\s\\;]");
            sb.append(a[2] + " " + a[1] + " (email: " + a[3] + ")" + System.lineSeparator());

        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

    public static String convert3(String input) {
        Pattern p = Pattern.compile("^?(.+;)(.+;)(.+)(@.+)");
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group(4).substring(1));
            Matcher m2 = p2.matcher(sb);
            if (!m2.find()) {
                sb.append(System.lineSeparator() + m.group(4).substring(1) + " ==> "
                        + m.group(1).substring(0, m.group(1).length() - 1));
            } else {
                Pattern p3 = Pattern.compile(m.group(4).substring(1) + ".+");
                Matcher m3 = p3.matcher(sb);
                m3.find();
                sb.insert(m3.end(), ", " + m.group(1).substring(0, m.group(1).length() - 1));
            }
        }
        return sb.toString().substring(2);

    }

    public static String convert4(String input) {

        final int numberOfRandom = 9999;
        final int numberOfCycles = 1001;
        SecureRandom rand = new SecureRandom();
        String[] text = input.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        sb.append(text[0] + ";Password" + System.lineSeparator());
        for (int i = 1; i < text.length; i++) {
            int random = rand.nextInt(numberOfRandom);
            while (random < numberOfCycles) {
                random = rand.nextInt(numberOfRandom);
            }
            sb.append(text[i] + ";" + random + System.lineSeparator());

        }
        return sb.toString();
    }
}
