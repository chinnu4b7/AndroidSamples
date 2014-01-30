package com.example.imagecachingsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] urls = new String[] {
				"http://hidefwalls.com/wp-content/g/hd-2/11285.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/16265.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/11285.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/16265.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/3d_abstr_23.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/5408_3d_space_scene_hd_wallpapers.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/abtsract_glow_hd-hd.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/11285.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/16265.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/3d_abstr_23.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/5408_3d_space_scene_hd_wallpapers.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/abtsract_glow_hd-hd.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/11285.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/16265.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/3d_abstr_23.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/5408_3d_space_scene_hd_wallpapers.jpg",
				"http://hidefwalls.com/wp-content/g/hd-2/abtsract_glow_hd-hd.jpg"
		};
		
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(new CustomAdapter(this, urls));
//		ImageView imageView = (ImageView) findViewById(R.id.imageview);
//		imageView.setImageBitmap(BitmapUtils.decodeSampleBitmapFromResource(getResources(),R.drawable.wall1, 100, 100));
//		imageView.setImageBitmap(BitmapUtils.downloadBitmap(""));
//		new BitmapDownloaderTask(this,imageView).execute("http://hidefwalls.com/wp-content/g/hd-2/11285.jpg");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
