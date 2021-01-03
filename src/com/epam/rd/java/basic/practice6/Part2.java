package com.epam.rd.java.basic.practice6;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Part2 {

    private static final int COUNT = 10_000;
    private int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    private final LinkedList<Integer> list;
    private final ArrayList<Integer> list2;
    Part2(){
        list = new LinkedList<>();
        list2 = new ArrayList<>();
        fill();
    }

    public void fill(){
        for(int i = 0; i < COUNT; i++){
            list.add(i);
            list2.add(i);
        }
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long time = System.currentTimeMillis();
        int deleteEl = 0;
        for(int i = list.size() - 1; i >= 0; i--){
            if (list.size() == 1) {
                break;
            }
            deleteEl += (k - 1);
            while (deleteEl >= list.size()) {
                deleteEl = deleteEl - list.size();
            }
            list.remove(deleteEl);
            //System.out.println(list);
        }

        return System.currentTimeMillis() - time;
    }

    public void show(){
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long then = System.nanoTime();
        int kk = 1;
        ListIterator<Integer> iter = list.listIterator();
        while (iter.hasNext()){
            iter.next();
            if (kk == k ){
                iter.remove();
                kk = 0;
                if (list.size() == 1){
                    return 0;
                }
            }
            kk++;
            if (!iter.hasNext() ){
                iter = list.listIterator();
            }
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
    }
    public static void main(String[] args) {
        Part2 part2 = new Part2();
        System.out.println("ArrayList#Index: " + removeByIndex(part2.list2, 4) + " ms");
        System.out.print("LinkedList#Index: " + removeByIndex(part2.list, 4) + " ms");
        Part2 part22 = new Part2();
        System.out.print("\nArrayList#Iterator: " + removeByIterator(part22.list2, 4) + " ms");
        System.out.print("\nLinkedList#Iterator: " + removeByIterator(part22.list, 4) + " ms");
    }
}

/*package com.epam.rd.java.basic.practice6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public final class Part2 {

    private static final int N = 17;

    private static final int K = 1;

    private final int listLength;

    private List<Integer> circleInArray;
    private List<Integer> circleInLinked;

    private Part2(int listLength) {
        this.listLength = listLength;
    }

    public static void main(String[] args) {

        Part2 part2 = new Part2(N);

        System.out.print("ArrayList#Index:" + " ");
        System.out.println(removeByIndex(part2.getCircleInArray(), K) + " ms");
        System.out.print("LinkedList#Index:" + " ");
        System.out.println(removeByIndex(part2.getCircleInLinked(), K) + " ms");

        System.out.print("ArrayList#Iterator:" + " ");
        System.out.println(removeByIterator(part2.getCircleInArray(), K) + " ms");
        System.out.print("LinkedList#Iterator:" + " ");
        System.out.println(removeByIterator(part2.getCircleInLinked(), K) + " ms");

    }

    public static long removeByIterator(List list, int k) {

        long time = System.currentTimeMillis();
        int count = 0;
        Iterator<Integer> it = list.iterator();
        while (list.size() > 1) {
            if (it.hasNext()) {
                it.next();
                count++;
                if (count == k) {
                    it.remove();
                    count = 0;
                }
            } else {
                it = list.iterator();
            }
        }
        return System.currentTimeMillis() - time;
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long time = System.currentTimeMillis();
        int local = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.size() == 1) {
                break;
            }

            local += (k - 1);

            while (local >= list.size()) {
                local = local - list.size();
            }
            list.remove(local);
        }
        return System.currentTimeMillis() - time;
    }

    public List<Integer> getCircleInArray() {
        circleInArray = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            circleInArray.add(i);
        }
        return new ArrayList<>(circleInArray);
    }

    public void setCircleInArray(List<Integer> circleInArray) {
        this.circleInArray = new ArrayList<>(circleInArray);
    }

    public List<Integer> getCircleInLinked() {
        circleInLinked = new LinkedList<>();
        for (int i = 0; i < listLength; i++) {
            circleInLinked.add(i);
        }
        return new LinkedList<>(circleInLinked);
    }

    public void setCircleInLinked(List<Integer> circleInLinked) {
        this.circleInLinked = new LinkedList<>(circleInLinked);
    }

}*/