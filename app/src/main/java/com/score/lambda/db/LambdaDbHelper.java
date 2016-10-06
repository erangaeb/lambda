package com.score.lambda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Perform creating tables here
 *
 * @author erangaeb@gmail.com(eranga herath)
 */
public class LambdaDbHelper extends SQLiteOpenHelper {

    private static final String TAG = LambdaDbHelper.class.getName();

    // we use singleton database
    private static LambdaDbHelper senzorsDbHelper;

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lambda.db";

    // data types, keywords and queries
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";

    private static final String SQL_CREATE_LAMBDA =
            "CREATE TABLE " + LambdaDbContract.Lambda.TABLE_NAME + " (" +
                    LambdaDbContract.Lambda._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " +
                    LambdaDbContract.Lambda.COLUMN_NAME_ID + TEXT_TYPE + ", " +
                    LambdaDbContract.Lambda.COLUMN_NAME_TIMESTAMP + INT_TYPE + ", " +
                    LambdaDbContract.Lambda.COLUMN_NAME_TEXT + TEXT_TYPE + ", " +
                    LambdaDbContract.Lambda.COLUMN_NAME_DELETED + INT_TYPE +
                    " )";

    private static final String SQL_DELETE_LAMBDA =
            "DROP TABLE IF EXISTS " + LambdaDbContract.Lambda.TABLE_NAME;

    /**
     * Init context
     * Init database
     *
     * @param context application context
     */
    public LambdaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * We are reusing one database instance in all over the app for better memory usage
     *
     * @param context application context
     * @return db helper instance
     */
    synchronized static LambdaDbHelper getInstance(Context context) {
        if (senzorsDbHelper == null) {
            senzorsDbHelper = new LambdaDbHelper(context.getApplicationContext());
        }
        return (senzorsDbHelper);
    }

    /**
     * {@inheritDoc}
     */
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate: creating db helper, db version - " + DATABASE_VERSION);
        Log.d(TAG, SQL_CREATE_LAMBDA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // enable foreign key constraint here
        Log.d(TAG, "OnConfigure: Enable foreign key constraint");
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * {@inheritDoc}
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        Log.d(TAG, "OnUpgrade: updating db helper, db version - " + DATABASE_VERSION);
        db.execSQL(SQL_DELETE_LAMBDA);

        onCreate(db);
    }

    /**
     * {@inheritDoc}
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
