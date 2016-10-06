package com.score.lambda.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LambdaUtils {
    public static String getData(long timeStamp) {
        Date date = new java.util.Date(timeStamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}
