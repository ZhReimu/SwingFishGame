package com.mypro.tools;

/**
 * 日志记录类
 *
 * @author Xiloerfan
 */
public class LogTools {
    /**
     * 把异常消息记录日志
     */
    public static void doLogForException(Exception e) {
        e.printStackTrace();
    }
}
