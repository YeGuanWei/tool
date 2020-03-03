package com.tool.controller;

/**
 * 随机数
 */
public class RandomNumberController {

    public static void main(String[] args) throws Exception {
        System.out.println((int) ((Math.random() * 9 + 1) * 100000));
    }

}
