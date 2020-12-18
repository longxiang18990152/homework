package com.example.sqlshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDBAdapter {

    private static final String DB_NAME = "product.db";
    private static final String DB_TABLE = "userinfo";
    private static final int DB_VERSION = 1;
    public static final String KEY_ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String BMPPATH = "bmppath";
    public static final String CONTENT = "content";


    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public ProductDBAdapter(Context _context) {
        context = _context;
        dbOpenHelper = new DBOpenHelper(context);
    }


    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                PRICE + "  text,  " + NAME + "  text not null, " + BMPPATH + "  text," + CONTENT + " text);";

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
            , String bmppath, String content) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("name", title);
            values.put("price", price);
            values.put("bmppath", bmppath);
            values.put("content", content);
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


    public List<Product> findAll() {
        List<Product> products = null;
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            products = new ArrayList<>();
            Cursor cursor = db.query(DB_TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Product info = new Product();
                info.setName(cursor.getString(cursor.getColumnIndex("name")));
                info.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                info.setBmppath(cursor.getString(cursor.getColumnIndex("bmppath")));
                info.setContent(cursor.getString(cursor.getColumnIndex("content")));
                products.add(info);
            }
            cursor.close();
            db.close();
        }
        return products;
    }

    /**
     * 更新
     *
     * @param name
     */
    public void update(String name, String newname, String price
            , String bmppath, String newcontent) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        if (db.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", newname);
            contentValues.put("price", price);
            contentValues.put("bmppath", bmppath);
            contentValues.put("content", newcontent);
            db.update(DB_TABLE, contentValues, "name=?", new String[]{name});
            db.close();
        }
    }

    //检验商品是否已存在
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

    public Product find(String name) {
        return null;
    }
}