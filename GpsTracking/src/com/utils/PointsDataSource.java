package com.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PointsDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allPoints = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_LATITUDE,MySQLiteHelper.COLUMN_LONGITUDE };

	  public PointsDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Points createPoint(double x,double y) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_LATITUDE, x);
	    values.put(MySQLiteHelper.COLUMN_LONGITUDE, y);
	    long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
	    		allPoints, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Points points = cursorToPoint(cursor);
	    cursor.close();
	    return points;
	  }

	  public void deletePoint(Points points) {
	    long id = points.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public ArrayList<Points> getAllPoints() {
		  ArrayList<Points> pointsList = new ArrayList<Points>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
	    		allPoints, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Points points = cursorToPoint(cursor);
	      pointsList.add(points);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return pointsList;
	  }

	  private Points cursorToPoint(Cursor cursor) {
		  Points points = new Points();
		  points.setId(cursor.getLong(0));
		  points.setLatitude(cursor.getFloat(1));
		  points.setLongitude(cursor.getFloat(2));
	    return points;
	  }
	} 