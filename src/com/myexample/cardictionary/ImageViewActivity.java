package com.myexample.cardictionary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);

		CarClass CarItem = (CarClass)getIntent().getSerializableExtra("Car_item");

		if (CarItem.getResourceID() != 0) {

			TextView textView = (TextView)findViewById(R.id.textView_detail);
			textView.setText(CarItem.getDescription());

			TextView textView_name = (TextView)findViewById(R.id.textView_name_detail);
			textView_name.setText(CarItem.getName());

			// カスタム PagerAdapter を生成
	        CustomPagerAdapter adapter = new CustomPagerAdapter(this);
	        adapter.add(CarItem.getResourceID());	// メインの画像を1枚目にセット

	        // 2枚目以降の画像があればセットする
	        char i = '2';
	        int NextImageID = getResources().getIdentifier(CarItem.getresourceName() + '_' + '1', "drawable", getPackageName());
	        while( NextImageID != 0 ) {
	        	adapter.add(NextImageID);
	        	NextImageID = getResources().getIdentifier(CarItem.getresourceName() + '_' + i, "drawable", getPackageName() );
	        	i++;
	        }

	        // ViewPager を生成
	        ViewPager viewPager = (ViewPager)findViewById(R.id.ViewPager);
	        viewPager.setAdapter(adapter);
		}

	}

}
