package com.epam.rd.java.basic.practice6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part63 {

    private String fileName;
    private String[] input;

    Part63() {
    }

    public static void main(String[] args) {

        new Part63().console();
    }

    private void console() {

        this.fileName = "part6.txt";
        initialize();

        if ("duplicates".equals("duplicates")) {
            getResultDublicates();
        }
    }

    private String getInput() {
        StringBuilder sb = new StringBuilder();
        try (Scanner file = new Scanner(new File(fileName), "CP1251")) {
            while (file.hasNext()) {
                sb.append(file.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.printf("File: %s not found%n", fileName);
        }
        return sb.toString();
    }

    public void initialize() {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(getInput());
        while (m.find()) {
            sb.append(m.group()).append(" ");
        }
        input = sb.toString().split(" ");
    }

    private void getResultDublicates() {
        final Map<String, Integer> wordCounts = new LinkedHashMap<>();
        for (String w : input) {
            wordCounts.merge(w, 1, Integer::sum);
        }
        int i = 0;
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i == 3) {
                break;
            }
            if (wordCount.getValue() > 1) {
                i++;
                System.out.println(new StringBuilder(wordCount.getKey()).reverse().toString().toUpperCase());
            }
        }
    }
}