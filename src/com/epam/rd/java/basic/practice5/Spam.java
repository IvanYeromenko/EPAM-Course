package com.epam.rd.java.basic.practice5;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Spam {
    private Thread[] threads;
    public Spam(String[] messages, int[] times) {
        if(messages.length==times.length) {
            threads=new Thread[messages.length];
            for(int i=0;i<messages.length;i++) {
                threads[i]=new Worker(messages[i], times[i]);
            }
        }
    }
    public void start() {
        for(int i=0;i<threads.length;i++) {
            threads[i].start();
        }
    }
    public void stop() {
        for(int i=0;i<threads.length;i++) {
            threads[i].interrupt();
        }
    }
    private static class Worker extends Thread {
        private String mes;
        private int time;
        public Worker(String mes,int time) {
            this.mes=mes;
            this.time=time;
        }

        @Override
        public void run() {
            while(true) {
                System.out.print(mes);
                try {
                    Worker.sleep(time);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam=new Spam(messages, times);
        spam.start();
        while(true) {
            try {
                if( System.in.read()==KeyEvent.VK_ENTER) {
                    spam.stop();
                    break;
                }
            } catch (IOException e) {
                System.err.println(Thread.currentThread().getName() + " is interrupted");
            }
        }

    }


}
