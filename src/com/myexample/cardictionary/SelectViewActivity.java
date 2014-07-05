package com.myexample.cardictionary;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SelectViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_view, menu);
		return true;
	}

}
