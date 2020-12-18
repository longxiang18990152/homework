package com.example.sqlshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDBAdapter {

    private static final String DB_NAME = "user.db";
    private static final String DB_TABLE = "userinfo";
    private static final int DB_VERSION = 1;
    public static final String KEY_ID = "id";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";


    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public UserDBAdapter(Context _context) {
        context = _context;
        dbOpenHelper = new DBOpenHelper(context);
    }


    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                NAME + "  text not null, " + PASSWORD + " text);";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(_db);
        }
    }


    /**
     * 添加一条记录
     */
    public void add(String name, String password
    ) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("password", password);
            //不允许插入一个空值,如果contentvalue,一般第二个参
            db.insert(DB_TABLE, null, values);//通过组拼完成的添加的操作
        }
        db.close();
    }

    //验证登录
    public boolean login(String username, String password) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "select * from  " + DB_TABLE + " where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String Query = "Select * from " + DB_TABLE + " where name =?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}