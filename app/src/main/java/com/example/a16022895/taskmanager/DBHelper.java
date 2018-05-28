package com.example.a16022895.taskmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Tasks.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_TASKS = "Tasks";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_TASKNAME = "task_name";
        private static final String COLUMN_TASKDETAIL = "task_detail";


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createNoteTableSql = "CREATE TABLE " + TABLE_TASKS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TASKNAME + " TEXT, "
                    + COLUMN_TASKDETAIL + " TEXT)";

            db.execSQL(createNoteTableSql);
            Log.i("INFO", "Tables created");

            ContentValues values = new ContentValues();
            values.put(COLUMN_TASKNAME, "Buy Milk");
            values.put(COLUMN_TASKDETAIL, "Low Fat");
            db.insert(TABLE_TASKS, null, values);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN module_name TEXT ");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
            onCreate(db);
        }

        public long insertTask(String taskName, String taskDetail) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( COLUMN_TASKNAME , taskName);
            values.put(COLUMN_TASKDETAIL, taskDetail);

            long result = db.insert(TABLE_TASKS, null, values);
            db.close();

            if (result == -1) {
                Log.d("DBHelper", "Insert failed");
            }

            Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
            return result;
        }

        public ArrayList<Task> getAllSongs() {
            ArrayList<Task> tasks = new ArrayList<Task>();

            String selectQuery = "SELECT " + COLUMN_ID + ", "
                    + COLUMN_TASKNAME + ", "
                    + COLUMN_TASKDETAIL
                    + " FROM " + TABLE_TASKS;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String taskName = cursor.getString(1);
                    String taskDetail = cursor.getString(2);

                    Task task = new Task(id, taskName, taskDetail);
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return tasks;
        }

    }

