package com.epam.rd.java.basic.practice6;

import java.util.*;

public class WordContainer {
    private static final String STOP  = "stop";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder str = new StringBuilder();
        String nextW;
        while(true) {
            nextW = scan.next();
            if(nextW.equals(STOP)) break;
            str.append(nextW).append(' ');
        }
        sortedResult(countFrequency(str));
    }
    public static List<Word> countFrequency(StringBuilder str) {
        String[] splitString = str.toString().trim().split(" ");
        List<Word> list = new ArrayList<>();
        List<Word> list2 = new ArrayList<>();
        for(String word : splitString) list.add(new Word(word, 1));
        for (Word word : list) {
            boolean flag = hasElement(list2, word);
            if (flag) list2.get(list2.indexOf(word)).addFrequency();
            else list2.add(word);
        }
        return list2;
    }
    public static boolean hasElement(List<Word> list, Word word) {
        for(Word wordW : list) {
            if (word.equals(wordW)) return true;
        }
        return false;
    }
    public static void sortedResult(List<Word> words) {
        if (!words.isEmpty()) {
            words.stream()
                    .sorted(Comparator.naturalOrder())
                    .forEach(s-> System.out.print(s + "\n"));
        }
    }
}
/*package com.epam.rd.java.basic.practice6.part1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class WordContainer extends TreeSet<Word>{

    private static final long serialVersionUID = -2634104525087620456L;

    public WordContainer(Comparator<? super Word> comparator) {
        super(comparator);
    }

    public static void main(String[] args) {
        Part1.wordCounter(System.in, System.out);
    }

    @Override
    public boolean add(Word wordW) {
        if (!contains(wordW)) {
            super.add(wordW);
            return false;
        }
        Iterator<Word> iterator = iterator();
        while (iterator.hasNext()) {
            Word next = iterator.next();
            if (comparator().compare(next, wordW) == 0) {
                next.setFrequency(next.getFrequency() + 1);
                return true;
            }
        }
        return false;
    }

    public Iterator<Word> frequencyIterator() {
        TreeSet<Word> words = new TreeSet<>(new Word.CompareByFrequency());
        for (Iterator<Word> iterator = iterator(); iterator.hasNext();) {
            Word next = iterator.next();

            words.add(next);
        }

        return words.iterator();
    }
}

*/