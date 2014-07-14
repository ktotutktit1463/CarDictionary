package com.myexample.cardictionary;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);

		CarClass CarItem = (CarClass)getIntent().getSerializableExtra("Car_item");

		if (CarItem.getResourceID() != 0) {
			/*
			ImageView imageView = (ImageView)findViewById(R.id.imageView_detail);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), CarItem.getResourceID()) );
			*/

			TextView textView = (TextView)findViewById(R.id.textView_detail);
			textView.setText(CarItem.getDescription());

			TextView textView_name = (TextView)findViewById(R.id.textView_name_detail);
			textView_name.setText(CarItem.getName());

			// カスタム PagerAdapter を生成
	        CustomPagerAdapter adapter = new CustomPagerAdapter(this);
	        adapter.add(Color.BLACK);
	        adapter.add(Color.RED);
	        adapter.add(Color.GREEN);
	        adapter.add(Color.BLUE);
	        adapter.add(Color.CYAN);
	        adapter.add(Color.MAGENTA);
	        adapter.add(Color.YELLOW);

	        // ViewPager を生成
	        ViewPager viewPager = new ViewPager(this);
	        viewPager.setAdapter(adapter);

	        // レイアウトにセット
	        setContentView(viewPager);
		}

	}

}
