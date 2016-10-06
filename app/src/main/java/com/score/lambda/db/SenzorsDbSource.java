package com.score.lambda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Do all database insertions, updated, deletions from here
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public class SenzorsDbSource {

    private static final String TAG = SenzorsDbSource.class.getName();

    private static Context context;

    /**
     * Init db helper
     *
     * @param context application context
     */
    public SenzorsDbSource(Context context) {
        Log.d(TAG, "Init: db source");
        this.context = context;
    }


    /**
     * Insert lambda to database
     *
     * @param lambda lambda
     */
    public void createLambda(com.score.lambda.pojo.Lambda lambda) {
        SQLiteDatabase db = SenzorsDbHelper.getInstance(context).getWritableDatabase();

        // content values to inset
        ContentValues values = new ContentValues();
        values.put(SenzorsDbContract.Lambda.COLUMN_NAME_ID, lambda.getId());
        values.put(SenzorsDbContract.Lambda.COLUMN_NAME_TIMESTAMP, lambda.getTimestamp());
        values.put(SenzorsDbContract.Lambda.COLUMN_NAME_TEXT, lambda.getText());
        values.put(SenzorsDbContract.Lambda.COLUMN_NAME_DELETED, 0);

        // Insert the new row, if fails throw an error
        db.insertOrThrow(SenzorsDbContract.Lambda.TABLE_NAME, SenzorsDbContract.Lambda.COLUMN_NAME_ID, values);
        db.close();
    }


}
