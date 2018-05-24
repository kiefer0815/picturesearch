package com.example.administrator.picturesearch.component.log;

/**
 * @author tangwei
 *         Created on 15/7/29
 */
public class RsLoggerManager {
    private static DefaultRsLogger logger = new DefaultRsLogger();

    public static RsLogger getLogger() {
        return logger;
    }
}
