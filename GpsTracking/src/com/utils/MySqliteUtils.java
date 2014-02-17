//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : GpsTracking
//      Created At   : 10-Feb-2014
//      Author       : prasad
//      Class Name   : MySqliteUtils.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.utils;

import android.provider.BaseColumns;

/**
 * @author prasad
 *
 */
public class MySqliteUtils {
	/**
	 * 
	 */
	public MySqliteUtils() {
	}
	
	public static abstract class ColumnEntry implements BaseColumns {
		public static final String TABLE_NAME = "GpsLocations";
		public static final String COLUMN_LATITUDE = "latitude";
		public static final String COLUMN_LONGITUDE = "longitude";
	}
}
