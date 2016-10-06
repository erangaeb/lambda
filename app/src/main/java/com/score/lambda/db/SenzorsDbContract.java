package com.score.lambda.db;

import android.provider.BaseColumns;

/**
 * Keep database table attributes
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public class SenzorsDbContract {

    public SenzorsDbContract() {
    }

    /* Inner class that defines sensor table contents */
    public static abstract class Lambda implements BaseColumns {
        public static final String TABLE_NAME = "lambda";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_DELETED = "deleted";
    }
}
