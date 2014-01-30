//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : ImageCachingSample
//      Created At   : 28-Jan-2014
//      Author       : prasad
//      Class Name   : ImageDownloader.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.example.imagecachingsample;

import com.example.imagecachingsample.BitmapUtils.DownloadedDrawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * @author prasad
 *
 */
public class ImageDownloader {
	private Context context;
	public ImageDownloader(Context context) {
		this.context = context;
	}
	
	/**
	 * @param url
	 * @param imageView
	 */
	public void download(String url, ImageView imageView) {
		
		if(cancelPotentialDownload(url, imageView)) {
			BitmapDownloaderTask bitmapDownloaderTask = new BitmapDownloaderTask(context,imageView);
			DownloadedDrawable downloadedDrawable = new DownloadedDrawable(bitmapDownloaderTask);
			imageView.setImageDrawable(downloadedDrawable);
			bitmapDownloaderTask.execute(url);
		}
	}
	
	/**
	 * @param url
	 * @param imageView
	 * @return Returns boolean which indicates whether the bitmap downaloder task is cancelled or not.
	 */
	private static boolean cancelPotentialDownload(String url,ImageView imageView) {
		BitmapDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);
		if(bitmapDownloaderTask != null) {
			String bitmapUrl = bitmapDownloaderTask.url;
			if(bitmapUrl == null || (!bitmapUrl.equals(url))) {
				bitmapDownloaderTask.cancel(true);
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param imageView
	 * @return Returns the BitmapDownloaderTask object to download the bitmap.
	 */
	public static BitmapDownloaderTask getBitmapDownloaderTask(ImageView imageView) {
		if (imageView != null) {
			Drawable drawable = imageView.getDrawable();
			if (drawable instanceof DownloadedDrawable) {
				DownloadedDrawable downloadedDrawable = (DownloadedDrawable) drawable;
				return downloadedDrawable.getBitmapDownloaderTask();
			}
		}
		return null;
	}
}
