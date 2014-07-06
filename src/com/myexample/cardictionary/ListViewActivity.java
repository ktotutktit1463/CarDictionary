package com.myexample.cardictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends Activity {
	
	private String SelectedMaker;
	private String SelectedType;

	/* ListViewに表示する要素のクラス */
	private class ListViewItem {
		private int resourceID;
		private String Name;
		private Bitmap img;
		
		public ListViewItem(int resource_id, String name, Bitmap img) {
			this.resourceID = resource_id;
			this.Name = name;
			this.img = img;
		}
		
		public int getResourceID() { return resourceID; }
		public String getName() { return Name; }
		public Bitmap getImage() { return img; }
	}
	
	/* ListViewにセットするアダプタのクラス */
	private class ListViewItemAdapter extends ArrayAdapter<ListViewItem> {
		private LayoutInflater layoutInflater;
		
		public ListViewItemAdapter(Context context, int textViewResourceId, List<ListViewItem> objects) {
			super(context, textViewResourceId, objects);
			layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		}
		
		/* ListViewの各行が表示する要素を返すメソッド */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null) {
				convertView = layoutInflater.inflate(R.layout.listview_item, null);
			}
			
			ListViewItem item = (ListViewItem)getItem(position);
			
			ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap( item.getImage() );
			
			TextView textView = (TextView)convertView.findViewById(R.id.textView_Item);
			textView.setText( item.getName() );
			
			return convertView;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		SelectedMaker = intent.getStringExtra("Maker");
		SelectedType = intent.getStringExtra("Type");
		
		Toast.makeText(this, SelectedMaker, Toast.LENGTH_LONG).show();
		
		setContentView(R.layout.listview);
		
		AssetManager as = getResources().getAssets();
		
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		
		try {
			InputStream is = as.open("cardata.csv");
			BufferedReader br = new BufferedReader( new InputStreamReader(is,"Shift_JIS") );
			
			String line;
			while ( (line = br.readLine()) != null ) {
				String str[] = line.split(",");
				
				if ( str[2].equalsIgnoreCase(SelectedMaker) ) {
					int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
					list.add( new ListViewItem(strId, str[0], BitmapFactory.decodeResource(getResources(), strId)) );
				}
			}
			
			br.close();
		} catch (IOException e) {
		}
		
		/*
		// ListViewに表示する要素を作成する
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		list.add( new ListViewItem(R.drawable.prius, "prius.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.prius) ));
		list.add( new ListViewItem(R.drawable.bents, "bents.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.bents) ));
		list.add( new ListViewItem(R.drawable.mazda, "mazda.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.mazda) ));
		list.add( new ListViewItem(R.drawable.fit, "fit.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.fit) ));
		list.add( new ListViewItem(R.drawable.insight, "インサイト", BitmapFactory.decodeResource(getResources(), R.drawable.insight) ));
		*/
		
		ListViewItemAdapter adapter = new ListViewItemAdapter(this, 0, list);
		
		ListView listView = (ListView)findViewById(R.id.listView);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView listView = (ListView)parent;
				ListViewItem item = (ListViewItem)listView.getItemAtPosition(position);
				
				Intent intent = new Intent(ListViewActivity.this, ImageViewActivity.class);
				intent.putExtra( "resourceID", item.getResourceID() );
				
				startActivity(intent);
			}
		});
	}

}
