package com.epam.rd.java.basic.practice6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part62 {

    private String fileName;
    private String[] input;

    Part62() {
    }

    public static void main(String[] args) {
        new Part62().console();
    }

    private void console() {

        this.fileName = "part6.txt";
        initialize();

        if ("length".equals("length")) {
            getResultLength();
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

    private void getResultLength() {
        class CountWithPlace {
            private final int lenth;
            private final int place;

            public CountWithPlace(int lenth, int place) {
                this.lenth = lenth;
                this.place = place;
            }

            public int getLenth() {
                return lenth;
            }

        }
        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            CountWithPlace countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new CountWithPlace(w.length(), place));
            }
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int length = countWithPlaceB.lenth - countWithPlaceA.lenth;
            if (length == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return length;
            }
        });
        sortedWords.putAll(wordCounts);

        int i = 0;
        for (String s : sortedWords.keySet()) {
            if (i == 3) {
                break;
            }
            i++;
            System.out.println(s + " ==> " + sortedWords.get(s).getLenth());
        }
    }

}
