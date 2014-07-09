package com.myexample.cardictionary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);

		Intent intent = getIntent();
		int resourceID = intent.getIntExtra("resourceID",0);

		if (resourceID != 0) {
			ImageView imageView = (ImageView)findViewById(R.id.imageView_detail);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), resourceID) );
		}

	}

}
