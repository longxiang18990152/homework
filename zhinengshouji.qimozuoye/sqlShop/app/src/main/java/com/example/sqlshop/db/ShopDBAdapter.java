package com.example.sqlshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ShopDBAdapter {

    private static final String DB_NAME = "shop.db";
    private static final String DB_TABLE = "shopinfo";
    private static final int DB_VERSION = 1;
    public static final String KEY_ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String NUMBER = "number";
    public static final String BMPPATH = "bmppath";


    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public ShopDBAdapter(Context _context) {
        context = _context;
        dbOpenHelper = new DBOpenHelper(context);
    }


    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                PRICE + "  text,  " + NAME + "  text not null, " + NUMBER + "  text," + BMPPATH + " text);";

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
    public void add(String title, String price
            , String number, String bmppath) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("name", title);
            values.put("price", price);
            values.put("number", number);
            values.put("bmppath", bmppath);
            //不允许插入一个空值,如果contentvalue,一般第二个参
            db.insert(DB_TABLE, null, values);//通过组拼完成的添加的操作
        }
        db.close();
    }

    /**
     * 删除数据
     *
     * @param
     */
    public void delete(String name) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(DB_TABLE, "name=?", new String[]{name});
            db.close();
        }
    }


    public List<ShopCar> findAll() {
        List<ShopCar> persons = null;
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            persons = new ArrayList<ShopCar>();

            Cursor cursor = db.query(DB_TABLE, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                ShopCar info = new ShopCar();
                info.setName(cursor.getString(cursor.getColumnIndex("name")));
                info.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                info.setNum(cursor.getString(cursor.getColumnIndex("number")));
                info.setBmppath(cursor.getString(cursor.getColumnIndex("bmppath")));
                persons.add(info);
            }
            cursor.close();
            db.close();
        }
        return persons;
    }


    public void deleteAllData() {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.delete(DB_TABLE, null, null);
    }

}