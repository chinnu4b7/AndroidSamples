package com.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_NAME = "points";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_LATITUDE = "latitude";
  public static final String COLUMN_LONGITUDE = "longitude";

  private static final String DATABASE_NAME = "points.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String TABLE_CREATE = "CREATE TABLE "
      + TABLE_NAME + "(" + COLUMN_ID
      + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LATITUDE + " TEXT NOT NULL,"
      + COLUMN_LONGITUDE + " TEXT NOT NULL);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }

} 
