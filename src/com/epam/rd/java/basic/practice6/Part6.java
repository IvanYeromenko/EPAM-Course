package com.epam.rd.java.basic.practice6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part6 {

    private String fileName;
    private String[] input;

    Part6() {
    }

    public static void main(String[] args) {
        new Part6().console(args[0], args[1], args[2], args[3]);
    }

    private void console(String input, String fileName, String task, String operation) {
        if (!("--input".equals(input) || "-i".equals(input))) {
            System.err.println("Wrong operation");
            return;
        }
        if (!("--task".equals(task) || "-t".equals(task))) {
            System.err.println("Wrong task");
            return;
        }

        this.fileName = fileName;
        initialize();

        switch (operation) {
            case "frequency":
                getResultFrequecy();
                break;
            case "length":
                getResultLength();
                break;
            case "duplicates":
                getResultDublicates();
                break;
            default:
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

            public int getLenth() {
                return lenth;
            }

            public CountWithPlace(int lenth, int place) {
                this.lenth = lenth;
                this.place = place;
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

    private void getResultFrequecy() {
        class CountWithPlace {
            private int count = 1;
            private final int place;

            public CountWithPlace(int place) {
                this.place = place;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            CountWithPlace countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new CountWithPlace(place));
            } else {
                countWithPlace.setCount(countWithPlace.getCount() + 1);
            }
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int count = countWithPlaceB.count - countWithPlaceA.count;
            if (count == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return count;
            }
        });
        sortedWords.putAll(wordCounts);

        TreeSet<String> firstStrings = new TreeSet<>(Comparator.reverseOrder());
        int i = 0;
        for (String s : sortedWords.keySet()) {
            if (i == 3) {
                break;
            }
            i++;
            firstStrings.add(s);
        }
        for (String s : firstStrings) {
            System.out.println(s + " ==> " + sortedWords.get(s).getCount());
        }
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
