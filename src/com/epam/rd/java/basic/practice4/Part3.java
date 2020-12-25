package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String REGEX = "(?<char>\\b\\p{L}\\b)|(?<string>\\b\\p{L}{2,}\\b)" +
            "|(?<double>\\d*\\.\\d+)|(?<int>\\d+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static boolean isStop;


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in, "cp1251")) {
            processUserInput(scanner, "part3.txt");
        }
    }

    public static void processUserInput(Scanner scanner, String fileName) {
        isStop = false;
        while (!isStop && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            processInput(line, fileName);
        }
    }

    public static void processInput(String line, String fileName) {
        if (line.isEmpty()) {
            return;
        }
        String fileContent = getFileContentAsString(fileName);

        switch (line.toLowerCase()) {
            case "char":
                System.out.print(getValues("char", fileContent));
                break;
            case "int":
                System.out.print(getValues("int", fileContent));
                break;
            case "double":
                System.out.print(getValues("double", fileContent));
                break;
            case "string":
                System.out.print(getValues("string", fileContent));
                break;
            case "stop":
                isStop = true;
                break;
            default:
                System.out.print("Incorrect input" + System.lineSeparator());
                break;
        }
    }

    public static String getValues(String groupName, String fileContent) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = PATTERN.matcher(fileContent);
        while (matcher.find()) {
            if (matcher.group(groupName) != null) {
                result.append(matcher.group(groupName)).append(" ");
            }
        }
        result.append(System.lineSeparator());
        return result.toString();
    }

    public static String getFileContentAsString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName), "cp1251")) {
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()).append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        return result.toString();
    }
}