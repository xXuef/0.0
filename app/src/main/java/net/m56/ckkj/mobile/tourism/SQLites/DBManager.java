package net.m56.ckkj.mobile.tourism.SQLites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.m56.ckkj.mobile.tourism.entity.DataCache;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by yue  on 2017/5/3.
 */

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     *
     * @param dataCaches
     */
    public void add(List<DataCache> dataCaches) {
        db.beginTransaction();  //开始事务
        try {
            for (DataCache dataCache : dataCaches) {
                db.execSQL("INSERT INTO data_cache VALUES(null,?)", new Object[]{dataCache.getUsername()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }


//    public void update(DataCache dataCaches) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", dataCaches.getUsername());
//        db.update("data_cache", cv, "_id = ?", new String[]{dataCaches.get_id()});
//    }

    /**
     * delete old
     *
     * @param dataCaches
     */
    public void deleteOld(DataCache dataCaches) {
        db.delete("data_cache", "_id=?", new String[]{String.valueOf(dataCaches.get_id())});
    }

    public void updata(String flag,String var,DataCache dataCaches) {
        ContentValues cv = new ContentValues();
        cv.put("username", var);
        db.update("data_cache",cv,flag+"=?",new String[]{dataCaches.getUsername()});
    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<DataCache> query() {
        ArrayList<DataCache> dataCache = new ArrayList<DataCache>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            DataCache dataCaches = new DataCache();
            dataCaches.set_id(c.getInt(c.getColumnIndex("_id")));
            dataCache.add(dataCaches);
        }
        c.close();
        return dataCache;
    }

    /**
     * query all , return cursor
     *
     * @return Cursor
     */
    private Cursor queryTheCursor() {
        //        context.startManagingCursor(c);
        return db.rawQuery("SELECT * FROM data_cache WHERE status = ?",new String[]{"0"});
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
