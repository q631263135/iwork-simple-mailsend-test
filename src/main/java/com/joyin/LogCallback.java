package com.joyin;


import java.util.Map;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/9/10 11:38
 */
public class LogCallback extends MailSendRecorder {


    public LogCallback(Map mailInfo) {
        super(mailInfo);
    }

    @Override
    public String failHandle(Throwable t) {
        String msg = String.format("发送邮件%s失败", mailInfo.get("MAIL"));
        return msg;
    }

    @Override
    public void success() {
        System.out.println(String.format("发送邮件%s成功", mailInfo.get("MAIL")));
    }


}
