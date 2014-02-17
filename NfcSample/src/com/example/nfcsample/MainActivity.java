package com.example.nfcsample;

import java.nio.charset.Charset;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;

public class MainActivity extends Activity {

	private String	url	= "http://developer.android.com/index.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	private NdefRecord createUriRecord() {
		NdefRecord uriRecord = new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, url.getBytes(Charset
				.forName("US-ASCII")), new byte[0], new byte[0]);
		return uriRecord;
	}

	@SuppressLint("NewApi")
	private NdefRecord createMimeRecord() {
		NdefRecord mimeRecord = NdefRecord.createMime("application/vnd.com.example.android.beam",
				"Hi mime record.".getBytes(Charset.forName("US-ASCII")));
		return mimeRecord;
	}

	NdefRecord createTextRecord(String payload, Locale locale, boolean encodeInUtf8) {
		byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
		Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF8") : Charset.forName("UTF16");
		byte[] textBytes = payload.getBytes(utfEncoding);
		int utfBit = encodeInUtf8 ? 1 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);
		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
		NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,
				new byte[0], data);
		return record;
	}

	private NdefMessage[]	msgs;

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			if (rawMsgs != null) {
				msgs = new NdefMessage[rawMsgs.length];
				for (int i = 0; i < rawMsgs.length; i++) {
					msgs[i] = (NdefMessage) rawMsgs[i];
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
