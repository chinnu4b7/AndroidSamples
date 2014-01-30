//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : ImageCachingSample
//      Created At   : 28-Jan-2014
//      Author       : prasad
//      Class Name   : CustomProgressBar.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.example.imagecachingsample;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

/**
 * @author prasad
 * 
 */
public class CustomProgressBar extends Dialog {

	/**
	 * @param context
	 */
	public CustomProgressBar(Context context) {
		super(context, R.style.CustomProgressBar);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @return
	 */
	public static Dialog show(Context context, String title,
			CharSequence message) {
		return show(context, title, message, false, false, null);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param intermediate
	 * @return
	 */
	public static Dialog show(Context context, String title,
			CharSequence message, boolean intermediate) {
		return show(context, title, message, intermediate, false, null);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param intermediate
	 * @param cancelable
	 * @return
	 */
	public static Dialog show(Context context, String title,
			CharSequence message, boolean intermediate, boolean cancelable) {
		return show(context, title, message, intermediate, cancelable, null);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param intermediate
	 * @param cancelable
	 * @param cancelListener
	 * @return
	 */
	public static Dialog show(Context context, String title,
			CharSequence message, boolean intermediate, boolean cancelable,
			OnCancelListener cancelListener) {

        CustomProgressBar dialog = new CustomProgressBar(context);
        dialog.setTitle(title);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        /* The next line will add the ProgressBar to the dialog. */
        dialog.addContentView(new ProgressBar(context), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        dialog.show();
		return dialog;
	}
}
