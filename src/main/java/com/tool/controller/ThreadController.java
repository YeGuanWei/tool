package com.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程
 */
@RestController
@RequestMapping("/api/thread")
public class ThreadController {

    private static long time = 60 * 1000;

    public static void main(String[] args) throws Exception {
        // normal();
        // sleep();
        // testWait();
    }

    @RequestMapping("/normal")
    public static String normal() throws Exception {
        System.out.println("收到Normal请求!");
        if (true) {
            throw new Exception("错误 - Normal");
        }
        return "normal response";
    }

    @RequestMapping("/sleep")
    public String sleep() throws Exception {
        System.out.println("收到Sleep请求!");
        Thread.sleep(time);
        if (true) {
            throw new Exception("错误 - Sleep");
        }
        return "sleep response";
    }

    @RequestMapping("/wait")
    public String testWait() {
        System.out.println("收到Wait请求!");
        TestWait t = new TestWait();
        t.start();

        return "wait response";
    }

}

class TestWait extends Thread {

    private static long time = 60 * 1000;

    public synchronized void run() {
        try {
            // 等待之后立即释放当前锁，并且进入等待池中等待唤醒
            // 当等待池中的线程被唤醒后，再次执行此语句之后的语句
            this.wait(time);
            throw new Exception("错误 - Wait Thread");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

