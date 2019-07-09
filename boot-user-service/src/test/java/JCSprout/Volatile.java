package JCSprout;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * Volatile
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-09 14:34
 */
@Slf4j
public class Volatile implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {

        }
        log.info(Thread.currentThread().getName() + "执行完毕");
    }

    private void stopThread() {
        flag = false;
    }

    public static void main(String[] args) {
        Volatile avolatie = new Volatile();
        new Thread(avolatie, "Thread A").start();

        log.info("main 线程正在运行");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String value = scanner.next();
            if (value.equals("'1")) {
                new Thread(() -> avolatie.stopThread()).start();
                break;
            }
        }
        System.out.println("主线程退出了！");
    }
}