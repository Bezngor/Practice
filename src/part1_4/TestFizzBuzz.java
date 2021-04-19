package part1_4;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class TestFizzBuzz {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(30);

        Runnable printFizz = () -> System.out.print("fizz,");
        Runnable printBuzz = () -> System.out.print("buzz,");
        Runnable printFizzBuzz = () -> System.out.print("fizzbuzz,");
        IntConsumer printNumber = num -> System.out.print(num + ",");

        Thread A = new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread B = new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread C = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread D = new Thread(() -> {
            try {
                fizzBuzz.number(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        A.start();
        B.start();
        C.start();
        D.start();
    }
}

class FizzBuzz {
    private int n;
    private Semaphore semNumber;
    private Semaphore semFizz;
    private Semaphore semBuzz;
    private Semaphore semFizzBuzz;

    public FizzBuzz(int n) {
        this.n = n;
        semNumber = new Semaphore(1);
        semFizz = new Semaphore(0);
        semBuzz = new Semaphore(0);
        semFizzBuzz = new Semaphore(0);
    }

    public void number (IntConsumer printNum) throws InterruptedException {
        for (int count = 1; count <= n; count++) {
            semNumber.acquire();

            if (count % 15 == 0) {
                semFizzBuzz.release();
            }

            else if (count % 5 == 0) {
                semBuzz.release();
            }

            else if (count % 3 == 0) {
                semFizz.release();
            }

            else {
                printNum.accept(count);
                semNumber.release();
            }
        }
    }

    public void fizzbuzz(Runnable printFB) throws InterruptedException {
        for (int count = 15; count <= n; count += 15) {
            semFizzBuzz.acquire();
            printFB.run();
            semNumber.release();
        }
    }

    public void buzz(Runnable printB) throws InterruptedException {
        for (int count = 5; count <= n; count += 5) {
            semBuzz.acquire();
            printB.run();

            if ((count + 5) % 3 == 0) {
                count += 5;
            }

            semNumber.release();
        }
    }

    public void fizz(Runnable printF) throws InterruptedException {
        for (int count = 3; count <= n; count += 3) {
            semFizz.acquire();
            printF.run();

            if ((count + 3) % 5 == 0) {
                count += 3;
            }

            semNumber.release();
        }
    }
}
