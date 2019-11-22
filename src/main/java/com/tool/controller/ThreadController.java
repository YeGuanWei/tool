package com.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程
 */
@RestController
@RequestMapping("/api/thread")
public class ThreadController {

    private static long time = 5 * 1000;

    public static void main(String[] args) throws Exception {
        // normal();
        // sleep();
        testWait();
    }

    @RequestMapping("/normal")
    public static void normal() throws Exception {
        throw new Exception("错误");
    }

    @RequestMapping("/sleep")
    public static void sleep() throws Exception {
        Thread.sleep(time);
        throw new Exception("错误");
    }

    @RequestMapping("/wait")
    public static void testWait() {
        TestWait t = new TestWait();
        t.start();
    }

}

class TestWait extends Thread {

    private static long time = 5 * 1000;

    public synchronized void run() {
        try {
            // 等待之后立即释放当前锁，并且进入等待池中等待唤醒
            // 当等待池中的线程被唤醒后，再次执行此语句之后的语句
            this.wait(time);
            throw new Exception("错误");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

