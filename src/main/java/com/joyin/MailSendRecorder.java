package com.joyin;


import java.util.Map;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/9/10 14:20
 */
public abstract class MailSendRecorder implements MailSendCallback {
    protected Map mailInfo;

    private StringBuilder sendMsg = new StringBuilder();

    public StringBuilder getSendMsg() {
        return sendMsg;
    }

    public MailSendRecorder(Map mailInfo) {
        this.mailInfo = mailInfo;
    }

    public abstract String failHandle(Throwable t);

    @Override
    public void fail(Throwable t) {
        sendMsg.append(this.failHandle(t)).append("：").append(t.getMessage());
        sendMsg.append("；");
    }

    public MailSendRecorder addRecord(String key, Object obj) {
        if (mailInfo != null) {
            mailInfo.put(key, obj);
        }
        return this;
    }

    public void updateMailInfo(Map mailSend) {
        this.mailInfo = mailSend;
    };
}
