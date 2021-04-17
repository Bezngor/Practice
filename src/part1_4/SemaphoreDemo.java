package part1_4;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> call(foo::first, "first"));
        Thread t2 = new Thread(() -> call(foo::second, "second"));
        Thread t3 = new Thread(() -> call(foo::third, "third"));

        t2.start();
        t1.start();
        t3.start();

        t3.join();
        t2.join();
        t1.join();
    }

    interface FooMethod {
        void call (Runnable print) throws InterruptedException;
    }

    private static void call(FooMethod method, String text) {
        try {
            method.call(() -> System.out.print(text));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Foo {
    public Foo() { }

    private static Semaphore sem1 = new Semaphore(1);
    private static Semaphore sem2 = new Semaphore(0);
    private static Semaphore sem3 = new Semaphore(0);

    public void first(Runnable print) {
        try {
            sem1.acquire();
            print.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem2.release();
        }
    }

    public void second(Runnable print) {
        try {
            sem2.acquire();
            print.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem3.release();
        }
    }

    public void third(Runnable print) {
        try {
            sem3.acquire();
            print.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem1.release();
        }
    }
}





