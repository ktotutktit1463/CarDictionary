package com.myexample.cardictionary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class SelectViewActivity extends Activity {

	private String SelectedMaker;
	private String SelectedType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectview);

        Spinner spinner1 = (Spinner)this.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)this.findViewById(R.id.spinner2);


        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner spinner = (Spinner)parent;
                SelectedMaker = (String)spinner.getSelectedItem();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }});

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner spinner = (Spinner)parent;
                SelectedType = (String)spinner.getSelectedItem();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_view, menu);
		return true;
	}

	// Searchボタンが押された時
	public void OnClick(View v) {
		Intent intent = new Intent(SelectViewActivity.this, ListViewActivity.class);
		intent.putExtra("Maker", SelectedMaker);
		intent.putExtra("Type", SelectedType);

		startActivity(intent);
	}

}
