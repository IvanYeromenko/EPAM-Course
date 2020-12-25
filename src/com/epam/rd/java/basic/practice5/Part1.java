package com.epam.rd.java.basic.practice5;

public final class Part1 {

    private Part1() {
    }

    public static void main(String[] args) {
        Thread myThreadFirst = new MyThreadFirst();
        myThreadFirst.setName("myThreadFirst");


        Thread myThreadTwo = new Thread(new MyThreadTwo());
        myThreadTwo.setName("myThreadTwo");

        try {
            myThreadFirst.start();
            myThreadFirst.join(1000);
            myThreadFirst.interrupt();
            System.err.println("Done Thread...\n");

            myThreadTwo.start();
            myThreadTwo.join(1000);
            myThreadTwo.interrupt();
            System.err.println("Done Runnable...\n");

            Thread.currentThread();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }

    }

    static class MyThreadFirst extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    sleep(333);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    static class MyThreadTwo implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(333);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                }
            }

        }

    }


}