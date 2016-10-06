package com.score.lambda.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility of lambda app
 *
 * @author erangaeb@gmail.com(eranga herath)
 */
public class LambdaUtils {
    /**
     * Convert timestamp to date
     *
     * @param timeStamp timestamp
     * @return date
     */
    public static String getData(long timeStamp) {
        Date date = new java.util.Date(timeStamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return sdf.format(date);
    }
}
