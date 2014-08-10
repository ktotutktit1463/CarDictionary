package com.myexample.cardictionary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

/* 車の詳細情報を表示するアクティビティ */
public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);

		/* intentから表示対象のCarClassを取得する */
		CarClass CarItem = (CarClass)getIntent().getSerializableExtra("Car_item");

		if (CarItem.getResourceID() != 0) {

			TextView textView = (TextView)findViewById(R.id.textView_detail);
			textView.setText(CarItem.getDescription());

			TextView textView_name = (TextView)findViewById(R.id.textView_name_detail);
			textView_name.setText(CarItem.getName());

			// カスタム PagerAdapter を生成
	        CustomPagerAdapter adapter = new CustomPagerAdapter(this);

	        // 画像をセット
	        adapter.add(CarItem.getResourceID());

	        // 2枚目以降の画像があれば動的にセットする
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
