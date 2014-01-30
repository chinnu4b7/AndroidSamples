//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : ImageCachingSample
//      Created At   : 28-Jan-2014
//      Author       : prasad
//      Class Name   : BitmapDownloaderTask.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.example.imagecachingsample;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @author prasad
 *
 */
public class BitmapDownloaderTask extends AsyncTask<String,Void,Bitmap>{

	String url;
	private final WeakReference<ImageView> imageReference;
//	private Context context;
	public BitmapDownloaderTask(Context context,ImageView imageView) {
		imageReference = new WeakReference<ImageView>(imageView);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		Utils.progressBar(context, true);
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		url = params[0];
		return BitmapUtils.downloadBitmap(url);
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);

//		Utils.progressBar(context, false);
		
		if (isCancelled()) {
			result = null;
		}
		
		if (imageReference != null) {
		    ImageView imageView = imageReference.get();
		    BitmapDownloaderTask bitmapDownloaderTask = ImageDownloader.getBitmapDownloaderTask(imageView);
		    // Change bitmap only if this process is still associated with it
		    if (this == bitmapDownloaderTask) {
		        imageView.setImageBitmap(result);
		    }
		}
	}
	
}
