package com.example.administrator.picturesearch.exception;

/**
 * Created by kiefer on 2017/6/19.
 */

public class TokenExpiredException extends RuntimeException {
        public TokenExpiredException(String msg) {
                super(msg);
        }
}
