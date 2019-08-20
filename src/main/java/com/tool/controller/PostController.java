package com.tool.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 发送Post请求
 */
public class PostController {

    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:8080/test";
        String param = "?id=123456&name=123456";
        System.out.println(sendPost(url, param));
    }

    public static String sendPost(String url, String param) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 设置通用请求的属性
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常");
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

}
