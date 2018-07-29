package com.nfl.threadlocal;

public class ThreadLocalTest {

    private static ThreadLocal<Student> threadLocal;

    public static void main(String[] args) {
        threadLocal = new ThreadLocal<Student>() {

            private Student student = new Student();

            @Override
            protected Student initialValue() {
                student.setAge(5);
                return student;
            }
        };

        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }

    private static class ThreadA implements Runnable{
        @Override
        public void run() {
            try {
                // do something
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().setAge(10);
        }
    }

    private static class ThreadB implements Runnable{
        @Override
        public void run() {
            try {
                // do something
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().setAge(15);
        }
    }
}
