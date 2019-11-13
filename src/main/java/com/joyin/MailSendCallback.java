package com.joyin;

/**
 * 发送邮件结果回调类
 * 不是真实的异步回调，发送过程中没有异常则执行success，失败执行fail
 *
 * @author yangchaozheng
 * @date 2019/9/10 11:32
 */
public interface MailSendCallback {
    void success();

    void fail(Throwable t);
}
