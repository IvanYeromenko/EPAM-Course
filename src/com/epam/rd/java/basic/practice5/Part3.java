package com.epam.rd.java.basic.practice5;

public class Part3 {

    private int counter;
    private int counter2;
    private final int numberOfIterations;
    Thread[] threads;

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
        threads = new Thread[numberOfThreads];
    }

    public static void main(final String[] args) {
        new Part3(2, 2).compare();
        new Part3(2, 2).compareSync();
    }

    public void compare() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(numberOfIterations, this);
            threads[i].start();
        }
        join();
    }

    public void compareSync() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SynchronizedWorker(numberOfIterations, this);
            threads[i].start();
        }
        join();
    }

    private void join() {
        for (Thread synchronizedWorker : threads) {
            try {
                synchronizedWorker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.print(e.getMessage());
            }
        }
    }

    private static class Worker extends Thread {
        final int iterations;
        final Part3 part3;

        public Worker(int iterations, Part3 part3) {
            this.iterations = iterations;
            this.part3 = part3;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                System.out.print((part3.counter == part3.counter2) + System.lineSeparator());
                part3.counter++;
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.print(e.getMessage());
                }
                part3.counter2++;
            }
        }
    }

    private static class SynchronizedWorker extends Worker {
        public SynchronizedWorker(int iterations, Part3 part3) {
            super(iterations, part3);
        }

        @Override
        public void run() {
            synchronized (part3) {
                super.run();
            }
        }
    }

}