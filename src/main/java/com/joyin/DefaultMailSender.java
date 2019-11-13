package com.joyin;

import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/8/31 15:33
 */
public class DefaultMailSender extends JavaMailSenderImpl {

    private MimeMessage mimeMessage;

    private MimeMessageHelper mimeMessageHelper;

    public DefaultMailSender() {
        super();
    }

    public void initSend(Properties prop) throws MessagingException {
        this.setJavaMailProperties(prop);
        this.setMimeMessage(this.createMimeMessage());
        this.setMimeMessageHelper(new MimeMessageHelper(mimeMessage, true,"UTF-8"));
    }

    public void setSend(String sendName, String sendFrom, String to, String subject, String content, boolean html) throws MessagingException {
        mimeMessageHelper.setFrom(new InternetAddress(sendName + " <" + sendFrom  + ">"));
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, html);
    }

    public void setSendFile(List<File> files) throws MessagingException {
        if (files != null && files.size() > 0) {
            for (File file : files) {
                mimeMessageHelper.addAttachment(file.getName(), file);
            }
        }
    }

    // setter getter
    public void send() throws MessagingException {
        this.send(mimeMessage);
    }


    public MimeMessage getMimeMessage() {
        return mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public MimeMessageHelper getMimeMessageHelper() {
        return mimeMessageHelper;
    }

    public void setMimeMessageHelper(MimeMessageHelper mimeMessageHelper) {
        this.mimeMessageHelper = mimeMessageHelper;
    }
}
