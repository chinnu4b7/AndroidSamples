package com.example.imagefiltering;

import java.io.IOException;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private Mat					src2, src1, dst;

	// private int alpha_slider;
	private double				alpha;
	private double				beta;

	/*
	 * private SeekBar contrastBar;
	 * private SeekBar brightnessBar;
	 */
	private SeekBar				blurBar;
	private static final int	ALPHA_SLIDER_MAX	= 100;
	private ImageView			mFilteredImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		/*
		 * mFilteredImage = (ImageView) findViewById(R.id.iv_normal);
		 * (blurBar = (SeekBar) findViewById(R.id.seekbar)).setOnSeekBarChangeListener(this);
		 */
		/*
		 * Bitmap bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
		 * Utils.bitmapToMat(bird, src);
		 * // src = Highgui.imread("file:///android_asset/bird.jpg");
		 * ((ImageView) findViewById(R.id.iv_normal)).setImageResource(R.drawable.bird);
		 * Imgproc.blur(src, dst, null);
		 * // Mat newImage = Mat.zeros(src1.size(), src1.type());
		 * //
		 * Bitmap bmp = Bitmap.createBitmap(dst.cols(), dst.rows(),Bitmap.Config.ARGB_8888);
		 * ((ImageView) findViewById(R.id.iv_filtered)).setImageBitmap(bmp);
		 * // Utils.matToBitmap(src1, bmp1);
		 */

		if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_8, this, mOpencvCallback)) {
			Log.e(TAG, "Cannot connect to OpenCV manager.");
		}
//		try {
//			src1 = Utils.loadResource(getApplicationContext(), R.drawable.bird);
//			src2 = Utils.loadResource(getApplicationContext(), R.drawable.ic_launcher);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 
	 */
	private void loadBitmap() {
		mFilteredImage = (ImageView) findViewById(R.id.iv_normal);
		(blurBar = (SeekBar) findViewById(R.id.seekbar)).setOnSeekBarChangeListener(this);
		try {
			src1 = Utils.loadResource(getApplicationContext(), R.drawable.bird);
			src2 = Utils.loadResource(getApplicationContext(), R.drawable.bird);
			dst = Utils.loadResource(getApplicationContext(), R.drawable.bird);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String	TAG				= "HI";
	private BaseLoaderCallback	mOpencvCallback	= new BaseLoaderCallback(this) {
													@Override
													public void onManagerConnected(int status) {
														switch (status) {
															case LoaderCallbackInterface.SUCCESS: {
																Log.i(TAG,
																		"Open CV loaded successfully.");
																setContentView(R.layout.activity_main);
																loadBitmap();
																break;
															}
															default: {
																super.onManagerConnected(status);
																break;
															}
														}
													}
												};

	private int					progress;

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		this.progress = progress;
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		alpha = (double) progress / ALPHA_SLIDER_MAX;
		beta = 1.0 - alpha;
		Core.addWeighted(src1, alpha, src2, beta, 0.0, dst);
		mFilteredImage.setImageBitmap(Bitmap.createBitmap(dst.cols(), dst.rows(),
				Bitmap.Config.ARGB_4444));
	}
	
	private void basicOperationsWithUImages() {
		try {
			Mat img = Utils.loadResource(getApplicationContext(), R.drawable.bird);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
