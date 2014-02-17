package com.example.gpstracking;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.utils.MyListAdapter;
import com.utils.Points;
import com.utils.PointsDataSource;

public class MainActivity extends FragmentActivity implements GooglePlayServicesClient.OnConnectionFailedListener,
GooglePlayServicesClient.ConnectionCallbacks, LocationListener{

	private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private static final int MILLISECONDS_PER_SECOND = 1000;
//	private static final int SECONDS_PER_MINIUTE = 60;
	private static final int UPDATE_INTERVAL_IN_MINIUTES = 5;
	private static final int UPDATE_INTERVAL = UPDATE_INTERVAL_IN_MINIUTES * MILLISECONDS_PER_SECOND;
	private static final int FASTEST_INTEVAL_IN_MINIUTES = 1;
	private static final int FASTEST_INTERVAL = FASTEST_INTEVAL_IN_MINIUTES * MILLISECONDS_PER_SECOND;
	
	LocationRequest mLocationRequest;
	LocationClient mLocationClient;
	boolean mUpdatesRequested;
	
	private SharedPreferences mPrefs;
	private Editor mEditor;
	
	private PointsDataSource mPointsDataSource;
	private ArrayList<Points> pointsList;
	private MyListAdapter mListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        mPointsDataSource = new PointsDataSource(this);
        mPointsDataSource.open();
        pointsList = mPointsDataSource.getAllPoints();
        
        mListAdapter = new MyListAdapter(this, pointsList);
        ((ListView) findViewById(R.id.listview)).setAdapter(mListAdapter);
		
		mLocationRequest = LocationRequest.create();
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		mLocationRequest.setInterval(UPDATE_INTERVAL);
		mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
		
		mPrefs = getSharedPreferences("GpsTracking",MODE_PRIVATE);
		mEditor = mPrefs.edit();
		
		mLocationClient = new LocationClient(this, this, this);
		
		mUpdatesRequested = false;
		
//		mLocationClient.requestLocationUpdates(mLocationRequest,this);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		mLocationClient.connect();
	}
	
	@Override
	protected void onPause() {
		mEditor.putBoolean("KEY_UPDATES_ON", mUpdatesRequested).commit();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(mPrefs.contains("KEY_UPDATES_ON")) {
			mUpdatesRequested = mPrefs.getBoolean("KEY_UPDATES_ON", false);
		} else {
			mEditor.putBoolean("KEY_UPDATES_ON", false).commit();
		}
	}
	
	@Override
	protected void onStop() {
		if(mLocationClient.isConnected()) {
			mLocationClient.removeLocationUpdates(this);
		}
		mLocationClient.disconnect();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public static class ErrorDialogFragment extends DialogFragment {
		private Dialog mDialog;
		
		/**
		 * 
		 */
		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}
		
		/**
		 * @param mDialog the mDialog to set
		 */
		public void setDialog(Dialog dialog) {
			this.mDialog = dialog;
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode) {
			switch (requestCode) {
			case CONNECTION_FAILURE_RESOLUTION_REQUEST:
				
				break;
				
			default:
				break;
			}
		}
	}
	
	@SuppressLint("NewApi")
	private boolean servicesConnected() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(ConnectionResult.SUCCESS == resultCode) {
			return true;
		} else {
			ConnectionResult connectionResult = new ConnectionResult(resultCode, null);
			int errorCode = connectionResult.getErrorCode();
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
			if (errorDialog != null) {
				ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
				dialogFragment.setDialog(errorDialog);
				dialogFragment.show(getFragmentManager(), "Location Updates");
			}
		}
		return false;
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		showToast("Connected");
		if(mUpdatesRequested) {
			mLocationClient.requestLocationUpdates(mLocationRequest, this);
		}
	}

	@Override
	public void onDisconnected() {
		showToast("Connection Failed.");
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if(result.hasResolution()) {
			try {
				result.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
			} catch (SendIntentException e) {
				e.printStackTrace();
			}
		}else {
			showDialog(result.getErrorCode());
		}
	}
	
	private void showToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		String message = "UpdatedLocation is: "+ latitude +","+ longitude;
		showToast(message);
		mPointsDataSource.createPoint(latitude, longitude);
		mListAdapter.notifyDataSetChanged();
	}
}
