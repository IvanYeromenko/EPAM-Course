package com.epam.rd.java.basic.practice3;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part3 {

    private static final String LS = System.lineSeparator();

    /**
     * Default constructor.
     */
    private Part3() {
        throw new UnsupportedOperationException("Subtask03");
    }

    public static String convert(String input) {
        String word;
        String pre;
        String post;
        String first = "";
        String lowerCase = "";
        String upperCase = "";
        final StringBuffer out = new StringBuffer();
        StringBuilder transWord;
        final String letter = "a-zA-Z\\p{InCyrillic}";
        final String regex = "(^|[^" + letter + "]*)([" + letter + "]+)([^" + letter + "]*|$)";

        final Matcher m = Pattern.compile(regex).matcher(input);
        while (m.find()) {
            pre = m.group(1);
            word = m.group(2);
            post = m.group(3);
            transWord = new StringBuilder(pre);
            if (word.length() > 2) {
                first = word.substring(0, 1);
                lowerCase = first.toLowerCase(Locale.getDefault());
                upperCase = first.toUpperCase(Locale.getDefault());
                first = (!first.equals(lowerCase)) ? lowerCase : upperCase;
                transWord.append(first).append(word.substring(1));
            } else {
                transWord.append(word);
            }
            transWord.append(post);
            m.appendReplacement(out, transWord.toString());
        }
        m.appendTail(out);

        String outString = out.toString();
        if (outString.endsWith(LS)) {
            outString = outString.trim();
        }

        return outString;
    }
}