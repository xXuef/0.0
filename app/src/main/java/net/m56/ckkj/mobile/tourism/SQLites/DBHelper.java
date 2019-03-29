package net.m56.ckkj.mobile.tourism.SQLites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create by 月 on 2017/5/3.
 */

public class DBHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tourist_cache.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_cache " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
