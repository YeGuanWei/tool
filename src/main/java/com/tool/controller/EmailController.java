package com.tool.controller;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 发送邮件
 */
public class EmailController {

    // 发送人邮箱
    private static String sender = "@qq.com";
    // 发送人姓名
    private static String senderName = "测试";
    // QQ邮箱开通的stmp服务后得到的客户端授权码
    private static String stmpAuthorizationCode = "";

    // 收件人
    private static String addressee = "@qq.com";

    // 邮件标题
    private static String title = "标题";
    // 邮件内容
    private static String text = "正文内容";
    // 邮件附件
    private static String[] files = {"C:/Develop/test.txt"};

    public static void main(String[] args) throws Exception {
        sendMail();
    }

    /**
     * QQ邮箱发送邮件(带附件)
     */
    public static void sendMail() throws Exception {
        Properties properties = new Properties();
        // 连接协议
        properties.put("mail.transport.protocol", "smtp");
        // 主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        // 端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        // 设置是否使用ssl安全连接
        properties.put("mail.smtp.ssl.enable", "true");
        // 设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.debug", "true");
        // 得到回话对象
        Session session = Session.getInstance(properties);

        // 获取邮件对象
        Message message = new MimeMessage(session);

        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(sender, senderName));
        // 设置收件人邮箱地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
        // 设置抄送人邮箱地址
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(addressee));


        // 设置邮件主题
        message.setSubject(title);

        // 添加正文内容
        Multipart multipart = new MimeMultipart();

        // 添加正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setText(text);
        contentPart.setHeader("Content-Type", "text/html; charset=utf-8");
        multipart.addBodyPart(contentPart);

        // 添加附件
        /*for (String file : files) {
            File usFile = new File(file);
            MimeBodyPart fileBody = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            fileBody.setDataHandler(new DataHandler(source));
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            fileBody.setFileName("=?GBK?B?" + enc.encode(usFile.getName().getBytes()) + "?=");
            multipart.addBodyPart(fileBody);
        }*/
        message.setContent(multipart);

        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(sender, stmpAuthorizationCode);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
