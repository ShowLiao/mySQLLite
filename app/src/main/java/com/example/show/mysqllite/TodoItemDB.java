package com.example.show.mysqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by show on 8/7/17.
 */

public class TodoItemDB extends SQLiteOpenHelper {

    private static TodoItemDB sInstance;

    //DB
    private static final String DB_NAME = "Todo1";
    private static final int DATABASE_VERSION = 1;

    //Table
    private static final String ITEM = "item";

    //Columns
    private static final String ID_COL = "id";
    private static final String TASK_COL = "task";
    private static final String DATE_COL = "dueDate";
    private static final String LOCATION_COL = "location";
    private static final String DETAIL_COL = "detail";
    private static final String TABLE_NAME = "TodoItem";
    private static final String PRIORITY_COL = "priority";

    public TodoItemDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public static synchronized TodoItemDB getsInstance(Context context) {

        if (null == sInstance) {
            sInstance = new TodoItemDB(context.getApplicationContext());
        }

        return sInstance;

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("====onCreate");

        String CREATE_TABLE = "Create Table " + TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_COL + " TEXT NOT NULL, " +
                DATE_COL + " TEXT, " +
                LOCATION_COL + " TEXT, " +
                DETAIL_COL + " TEXT, " +
                PRIORITY_COL + " INTEGER NOT NULL)";

        Log.e("==SQL:", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABL IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addItem(Item item) {

        Log.e("==addItem:", "1");
        SQLiteDatabase db = getWritableDatabase();
        Log.e("==addItem:", "2");
        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(TASK_COL, item.getTask());
            values.put(DATE_COL, item.getDate());
            values.put(LOCATION_COL, item.getLocation());
            values.put(DETAIL_COL, item.getDetail());
            values.put(PRIORITY_COL, item.getPriority());

            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    private void updateInfo(Item item) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TASK_COL, item.getTask());
            values.put(DATE_COL, item.getDate());
            values.put(LOCATION_COL, item.getLocation());
            values.put(DETAIL_COL, item.getDetail());
            values.put(PRIORITY_COL, item.getPriority());

            int row = db.update(TABLE_NAME, values, ID_COL + "= " + item.getId(), null);
            if (row > 0) {
                System.out.print("update successed!!");
            }
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    private void delItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {

            int row = db.delete(TABLE_NAME, ID_COL + "= " + item.getId(), null);
            if (row > 0) {
                System.out.print("delete successed!!");
            }
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Item> queryAll() {
        ArrayList<Item> list = new ArrayList<>();

        String QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        db.beginTransaction();

        Log.e("QueryAll:" , QUERY);
        try {
            Cursor cursor = db.rawQuery(QUERY, null);

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    Item item = new Item();

                    item.setId(cursor.getInt(0));
                    item.setTask(cursor.getString(1));
                    item.setDate(cursor.getString(2));
                    item.setLocation(cursor.getString(3));
                    item.setDetail(cursor.getString(4));
                    item.setPriority(cursor.getInt(5));
                    Log.d("id:", String.valueOf(cursor.getInt(0)));
                    Log.d("task", cursor.getString(1));
                    Log.d("date", cursor.getString(2));
                    Log.d("location", cursor.getString(3));
                    Log.d("detail", cursor.getString(4));
                    list.add(item);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }
}
