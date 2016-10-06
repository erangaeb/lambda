package com.score.lambda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.score.lambda.pojo.Lambda;

/**
 * Do all database insertions, updated, deletions from here
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public class LambdaDbSource {

    private static final String TAG = LambdaDbSource.class.getName();

    private static Context context;

    /**
     * Init db helper
     *
     * @param context application context
     */
    public LambdaDbSource(Context context) {
        Log.d(TAG, "Init: db source");
        this.context = context;
    }

    /**
     * Insert lambda to database
     *
     * @param lambda lambda
     */
    public void createLambda(Lambda lambda) {
        SQLiteDatabase db = LambdaDbHelper.getInstance(context).getWritableDatabase();

        // content values to inset
        ContentValues values = new ContentValues();
        values.put(LambdaDbContract.Lambda.COLUMN_NAME_ID, lambda.getId());
        values.put(LambdaDbContract.Lambda.COLUMN_NAME_TIMESTAMP, lambda.getTimestamp());
        values.put(LambdaDbContract.Lambda.COLUMN_NAME_TEXT, lambda.getText());
        values.put(LambdaDbContract.Lambda.COLUMN_NAME_DELETED, 0);

        // Insert the new row, if fails throw an error
        db.insertOrThrow(LambdaDbContract.Lambda.TABLE_NAME, LambdaDbContract.Lambda.COLUMN_NAME_ID, values);
        db.close();
    }

    /**
     * Mark lambda as deleted
     *
     * @param lambda lambda
     */
    public void delteLambda(Lambda lambda) {
        SQLiteDatabase db = LambdaDbHelper.getInstance(context).getWritableDatabase();

        // content values to inset
        ContentValues values = new ContentValues();
        values.put(LambdaDbContract.Lambda.COLUMN_NAME_DELETED, 1);

        // update
        db.update(LambdaDbContract.Lambda.TABLE_NAME,
                values,
                LambdaDbContract.Lambda.COLUMN_NAME_ID + " = ?",
                new String[]{lambda.getId()});

        db.close();
    }

    /**
     * Find all non deleted lambdas from db
     */
    public void getAllLambdas() {

    }

}
