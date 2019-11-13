package com.joyin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.*;

public class MailSentUtil {
    private static String host;
    private static String username;
    private static String password;
    private static String sendFrom;
    private static String sendName;
    private static String switchqq;

    private static final String SMPT = "smtp";
    
    private static Properties mailConfig;

    static {
        try {
            InputStreamReader inStream = new InputStreamReader(MailSentUtil.class.getClassLoader()
                    .getResourceAsStream("mail.properties"), "UTF-8");
            BufferedReader bf = new BufferedReader(inStream);
            mailConfig = new Properties();
            mailConfig.load(bf);
            host = mailConfig.getProperty("mail.host");
            username = mailConfig.getProperty("mail.username");
            password = mailConfig.getProperty("mail.password");
            sendFrom = mailConfig.getProperty("mail.sendFrom");
            sendName = mailConfig.getProperty("mail.sendName");
            switchqq = mailConfig.getProperty("switchqq");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendQQMail(String to, String subject, String content, List<File> fileList, MailSendCallback callback) {
        try {
            if (switchqq.equals("1")) {
                QQMailSender mailSender = new QQMailSender();
                mailSender.initSend(mailConfig, username, "yzzphxmztpukbbfd");

                mailSender.setSend(sendName, sendFrom, to, subject, content, true);
                mailSender.setSendFile(fileList);

                mailSender.send();
                callback.success();
                return true;
            } else {
                send(to, subject, content, true, fileList, callback);
            }

        } catch (Exception e) {
            callback.fail(e);
            e.printStackTrace();
        }

        return false;
    }

    public static boolean send(String to, String subject, String content, boolean html, List<File> fileList,
                               MailSendCallback callback) {
        try {
            DefaultMailSender mailSender = new DefaultMailSender();
            mailSender.initSend(mailConfig);

            mailSender.setSend(sendName, sendFrom, to, subject, content, html);
            mailSender.setSendFile(fileList);
            mailSender.send();

            callback.success();
            return true;
        } catch (MessagingException e) {
            callback.fail(e);
            e.printStackTrace();
        }

        return false;
    }

    public static boolean sendQQMailHtmlFalse(String to, String subject, String content, List<File> fileList, MailSendCallback callback) {
        try {
            QQMailSender mailSender = new QQMailSender();
            mailSender.initSend(mailConfig, username, "yzzphxmztpukbbfd");

            mailSender.setSend(sendName, sendFrom, to, subject, content, false);
            mailSender.setSendFile(fileList);

            mailSender.send();
            callback.success();
            return true;
        } catch (Exception e) {
            callback.fail(e);
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            List<File> files = new ArrayList<File>();

            MailSentUtil.sendQQMail("418054605@qq.com", "资管产品管理系统提示", "<h1>测试内容</h1>", files,
                    new LogCallback(new HashMap<>()));
            MailSentUtil.sendQQMail("631263135@qq.com", "资管产品管理系统提示", "<h1>测试内容</h1>", files,
                    new LogCallback(new HashMap<>()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
