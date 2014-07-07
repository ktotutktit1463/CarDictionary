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
		
		//Toast.makeText(this, SelectedMaker, Toast.LENGTH_LONG).show();
		
		setContentView(R.layout.listview);
		
		// listviewに表示する要素をassetsのcardata.csvから取得する
		AssetManager as = getResources().getAssets();
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		try {
			InputStream is = as.open("cardata.csv");
			BufferedReader br = new BufferedReader( new InputStreamReader(is,"Shift_JIS") );
			
			String line;
			// cardata.csvの内容を1行ずつ読み込む
			while ( (line = br.readLine()) != null ) {
				String str[] = line.split(",");
				String type[] = str[3].split("&");
				
				// 選択されたメーカーとタイプが「選択なし」の場合　csvの全てをlistにセット
				if ( SelectedMaker.equalsIgnoreCase("---") & SelectedType.equalsIgnoreCase("---") ){
					int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
					list.add( new ListViewItem(strId, str[0], BitmapFactory.decodeResource(getResources(), strId)) );
				}
				// 選択されたメーカーだけが「選択なし」の場合 選択されたタイプに合うものだけをリストにセット
				else if ( SelectedMaker.equalsIgnoreCase("---") ) {
					if ( SelectedType.equalsIgnoreCase(type[0]) || SelectedType.equalsIgnoreCase(type[1]) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new ListViewItem(strId, str[0], BitmapFactory.decodeResource(getResources(), strId)) );
					}
				}
				// 選択されたタイプだけが「選択なし」の場合 選択されたメーカーに合うものだけをリストにセット
				else if ( SelectedType.equalsIgnoreCase("---") ) {
					if ( SelectedMaker.equalsIgnoreCase(str[2]) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new ListViewItem(strId, str[0], BitmapFactory.decodeResource(getResources(), strId)) );
					}
				}
				// メーカーもタイプも指定されている場合、両方に合ったものをリストにセット
				else {
					if ( SelectedMaker.equalsIgnoreCase(str[2]) & (SelectedType.equalsIgnoreCase(type[0]) || SelectedType.equalsIgnoreCase(type[1])) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new ListViewItem(strId, str[0], BitmapFactory.decodeResource(getResources(), strId)) );
					}
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
