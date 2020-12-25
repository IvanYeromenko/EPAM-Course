package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Part5 implements Callable<Object> {

    private static final Object MONITOR = new Object();
    private static final int THREADS_NUMBER = 10;
    private static final int COLUMNS = 20;
    private static final int EOL_LENGTH = System.lineSeparator().length();
    private static final String CHARSET_NAME = "Cp1251";

    private final RandomAccessFile out;
    private final int position;
    private final String characters;

    public Part5(RandomAccessFile out, int position, String charToFill, int stringLength) {
        this.out = out;
        this.position = position;

        StringBuilder sb = new StringBuilder(stringLength);
        for (int i = 0; i < COLUMNS; i++) {
            sb.append(charToFill);
        }
        sb.append(System.lineSeparator());
        characters = sb.toString();
    }

    @Override
    public Object call() throws IOException {
        RandomAccessFile raf = getRAF();
        synchronized (MONITOR) {
            raf.seek(position);
            raf.write(characters.getBytes(CHARSET_NAME));
        }
        return null;
    }

    private RandomAccessFile getRAF() {
        return out;
    }

    public static void main(String[] args) {
        String fileName = "part5.txt";
        try {
            Files.deleteIfExists(new File(fileName).toPath());
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        File file = new File(fileName);

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        ExecutorService exe = Executors.newFixedThreadPool(THREADS_NUMBER);
        try {
            ArrayList<Future> futures = new ArrayList<>();

            int position = 0;
            for (int currentNumber = 0; currentNumber < THREADS_NUMBER; currentNumber++) {
                String charToFill = String.valueOf(currentNumber);
                int charsLength = COLUMNS * charToFill.length();
                int stringLength = charsLength + EOL_LENGTH;

                Future f = exe.submit(new Part5(raf, position, charToFill, stringLength));
                futures.add(f);
                position += stringLength;
            }

            for (Future future : futures) {
                future.get();
            }
        } catch ( InterruptedException | ExecutionException e) {

            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        } finally {
            exe.shutdown();
            try {
                assert raf != null;
                raf.close();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        }
        try {
            System.out.println(new String(Files.readAllBytes(file.toPath()), CHARSET_NAME));
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
    }
}
