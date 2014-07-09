package com.myexample.cardictionary;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		
		CarClass CarItem = (CarClass)getIntent().getSerializableExtra("Car_item");

		if (CarItem.getResourceID() != 0) {
			ImageView imageView = (ImageView)findViewById(R.id.imageView_detail);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), CarItem.getResourceID()) );
			
			TextView textView = (TextView)findViewById(R.id.textView_detail);
			textView.setText(CarItem.getDescription());
			
			TextView textView_name = (TextView)findViewById(R.id.textView_name_detail);
			textView_name.setText(CarItem.getName());
		}

	}

}
