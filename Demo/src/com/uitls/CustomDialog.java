//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : Demo
//      Created At   : 27-Jan-2014
//      Author       : prasad
//      Class Name   : CustomDialog.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.uitls;

import com.example.demo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author prasad
 * 
 */
@SuppressLint({ "NewApi", "ValidFragment" })
public class CustomDialog extends DialogFragment {

	String message;
	public CustomDialog(String message) {
		this.message = message;
	}
	
	public interface CustomDialogListeners {
		void onPositiveButtonClick();

		void onNegativeButtonClick();
	}

	private CustomDialogListeners customDialogListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			customDialogListener = (CustomDialogListeners) activity;
		} catch (ClassCastException e) {
			Toast.makeText(getActivity(), activity.toString()
					+ " must implement NoticeDialogListener", Toast.LENGTH_SHORT).show();
//			throw new ClassCastException(activity.toString()
//					+ " must implement NoticeDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(message).setTitle(getString(R.string.app_name))
		.setPositiveButton(android.R.string.ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				customDialogListener.onPositiveButtonClick();
			}
		}).setNegativeButton(android.R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				customDialogListener.onNegativeButtonClick();
			}

		});
		return builder.create();
	}
}