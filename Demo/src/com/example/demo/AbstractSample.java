package com.example.demo;

import android.util.Log;

public abstract class AbstractSample {
	private static final String TAG = "HI";
	public AbstractSample() {
		Log.e(TAG, "--abstract constructor--");
	}
	
	public int add(int a, int b) {
		return a+b;
	}
}
