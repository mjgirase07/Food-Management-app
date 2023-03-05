package com.example.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sample.model.Item;
import com.example.sample.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {


    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.ITEM_ID + " INTEGER PRIMARY KEY, " + Params.ITEM_NAME
                + " TEXT," + Params.ITEM_WEIGHT + " TEXT" + ")";

        String createTable = "CREATE TABLE " + Params.TABLENAME + "("
                + Params.ITEM_ID + " INTEGER PRIMARY KEY, " + Params.ITEM_NAME
                + " TEXT, " + Params.ITEM_WEIGHT + " TEXT, " + Params.ITEM_EXPIRY + " TEXT, " + Params.ITEM_FREQUENCY + " INTEGER "+")";

        Log.d("dbdhruv","Query Being Run is :"+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        String create1 = "ALTER TABLE " + Params.TABLE_NAME + " ADD " + Params.ITEM_EXPIRY + " TEXT";
//        String create2 = "ALTER TABLE " + Params.TABLE_NAME + " ADD " + Params.ITEM_FREQUENCY + " INTEGER";
//        if(i1>i){
//            db.execSQL(create1);
//            db.execSQL(create2);
//        }
    }

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.ITEM_ID, item.getItem_id());
        values.put(Params.ITEM_NAME, item.getItem_name());
        values.put(Params.ITEM_WEIGHT, item.getItem_weight());
        values.put(Params.ITEM_EXPIRY, item.getItem_expiry());
        values.put(Params.ITEM_FREQUENCY, item.getItem_frequency());

        db.insert(Params.TABLE_NAME,null,values);
        Log.d("dbdhruv","Successfully Inserted");
        //db.close();
    }

    public List<Item> getAllItems(){
        List <Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setItem_id(Integer.parseInt(cursor.getString(0)));
                item.setItem_name(cursor.getString(1));
                item.setItem_weight(cursor.getString(2));
                item.setItem_expiry(cursor.getString(3));
                item.setItem_frequency(cursor.getInt(4));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }

    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.ITEM_NAME,item.getItem_name());
        values.put(Params.ITEM_WEIGHT,item.getItem_weight());

        return db.update(Params.ITEM_ID,values,Params.ITEM_ID + "=?", new String[]{String.valueOf(item.getItem_id())});
    }

    public void deleteContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.ITEM_ID+"=?", new String[]{String.valueOf((id))});
        //db.close();
    }
    public void deleteContact(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.ITEM_ID+"=?", new String[]{String.valueOf((name))});
        //db.close();
    }
    Cursor c = null,c2,c3,c4,cs = null;
    public Cursor ret_id(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        c = db.rawQuery("SELECT itemid from STOCK_DETAILS where item = "+"'"+Name+"'",new String[]{});
        return c;
    }
    public Cursor ret_weight(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        c2 = db.rawQuery("SELECT weight from STOCK_DETAILS where item = "+"'"+Name+"'",new String[]{});
        return c2;
    }
    public Cursor ret_expiry(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        c3 = db.rawQuery("SELECT date from STOCK_DETAILS where item = "+"'"+Name+"'",new String[]{});
        return c3;
    }
    public Cursor ret_frequency(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        c4 = db.rawQuery("SELECT frequency from STOCK_DETAILS where item = "+"'"+Name+"'",new String[]{});
        return c4;
    }

    public Cursor ret_all(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        cs = db.rawQuery("SELECT * from STOCK_DETAILS where item = "+"'"+Name+"'",new String[]{});
        return cs;
    }
}