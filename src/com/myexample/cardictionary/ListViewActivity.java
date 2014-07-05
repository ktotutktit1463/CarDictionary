package com.myexample.cardictionary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

	/* ListViewに表示する要素のクラス */
	private class ListViewItem {
		private int resourceID;
		private String fileName;
		private Bitmap img;
		
		public ListViewItem(int resource_id, String file_name, Bitmap img) {
			this.resourceID = resource_id;
			this.fileName = file_name;
			this.img = img;
		}
		
		public int getResourceID() { return resourceID; }
		public String getFileName() { return fileName; }
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
			textView.setText( item.getFileName() );
			
			return convertView;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listview);
		
		// ListViewに表示する要素を作成する
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		list.add( new ListViewItem(R.drawable.prius, "prius.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.prius) ));
		list.add( new ListViewItem(R.drawable.bents, "bents.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.bents) ));
		list.add( new ListViewItem(R.drawable.mazda, "mazda.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.mazda) ));
		
		ListViewItemAdapter adapter = new ListViewItemAdapter(this, 0, list);
		
		ListView listView = (ListView)findViewById(R.id.listView);
		
		listView.setAdapter(adapter);
		
	}

}
