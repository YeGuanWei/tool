package com.tool.controller.designPattern;

/**
 * 单例模式 - 懒汉式
 */
public class SingletonController {

    // 保证 instance 在所有线程中同步
    private static volatile SingletonController instance = null;

    // private 避免类在外部被实例化
    private SingletonController() {
    }

    public static synchronized SingletonController getInstance() {
        // getInstance 方法前加同步
        if (instance == null) {
            instance = new SingletonController();
        }
        return instance;
    }

}
