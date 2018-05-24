package com.example.administrator.picturesearch.exception.chat;

/**
 * User: zhaohaifeng
 * Date: 16/9/29
 * Time: 下午4:58
 */

public abstract class AbstractChatException extends RuntimeException {
    public AbstractChatException(String msg) {
        super(msg);
    }
}
