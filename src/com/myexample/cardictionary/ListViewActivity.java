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

	/* ListView�ɃZ�b�g����A�_�v�^�̃N���X */
	private class ListViewItemAdapter extends ArrayAdapter<CarClass> {
		private LayoutInflater layoutInflater;

		public ListViewItemAdapter(Context context, int textViewResourceId, List<CarClass> objects) {
			super(context, textViewResourceId, objects);
			layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		/* ListView�̊e�s���\������v�f��Ԃ����\�b�h */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if(convertView == null) {
				convertView = layoutInflater.inflate(R.layout.listview_item, null);
			}

			CarClass item = (CarClass)getItem(position);

			ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), item.getResourceID()) );

			TextView textView_name = (TextView)convertView.findViewById(R.id.textView_Name);
			textView_name.setText( item.getName() );

			TextView textView_description = (TextView)convertView.findViewById(R.id.textView_description);
			textView_description.setText( item.getDescription() );

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

		// listview�ɕ\������v�f��assets��cardata.csv����擾����
		AssetManager as = getResources().getAssets();
		List<CarClass> list = new ArrayList<CarClass>();
		try {
			InputStream is = as.open("cardata.csv");
			BufferedReader br = new BufferedReader( new InputStreamReader(is,"UTF-8") );

			String line;
			// cardata.csv�̓��e��1�s���ǂݍ���
			while ( (line = br.readLine()) != null ) {
				String str[] = line.split(",");
				String type[] = str[3].split("&");

				// �I�����ꂽ���[�J�[�ƃ^�C�v���u�I���Ȃ��v�̏ꍇ�@csv�̑S�Ă�list�ɃZ�b�g
				if ( SelectedMaker.equalsIgnoreCase("---") & SelectedType.equalsIgnoreCase("---") ){
					int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
					list.add( new CarClass(strId, str[0], str[4]) );
				}
				// �I�����ꂽ���[�J�[�������u�I���Ȃ��v�̏ꍇ �I�����ꂽ�^�C�v�ɍ������̂��������X�g�ɃZ�b�g
				else if ( SelectedMaker.equalsIgnoreCase("---") ) {
					if ( SelectedType.equalsIgnoreCase(type[0]) || SelectedType.equalsIgnoreCase(type[1]) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new CarClass(strId, str[0], str[4]) );
					}
				}
				// �I�����ꂽ�^�C�v�������u�I���Ȃ��v�̏ꍇ �I�����ꂽ���[�J�[�ɍ������̂��������X�g�ɃZ�b�g
				else if ( SelectedType.equalsIgnoreCase("---") ) {
					if ( SelectedMaker.equalsIgnoreCase(str[2]) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new CarClass(strId, str[0], str[4]) );
					}
				}
				// ���[�J�[���^�C�v���w�肳��Ă���ꍇ�A����ɍ��������̂����X�g�ɃZ�b�g
				else {
					if ( SelectedMaker.equalsIgnoreCase(str[2]) & (SelectedType.equalsIgnoreCase(type[0]) || SelectedType.equalsIgnoreCase(type[1])) ) {
						int strId = getResources().getIdentifier(str[1], "drawable", getPackageName() );
						list.add( new CarClass(strId, str[0], str[4]) );
					}
				}
			}
			br.close();
		} catch (IOException e) {
		}

		/*
		// ListView�ɕ\������v�f���쐬����
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		list.add( new ListViewItem(R.drawable.prius, "prius.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.prius) ));
		list.add( new ListViewItem(R.drawable.bents, "bents.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.bents) ));
		list.add( new ListViewItem(R.drawable.mazda, "mazda.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.mazda) ));
		list.add( new ListViewItem(R.drawable.fit, "fit.jpg", BitmapFactory.decodeResource(getResources(), R.drawable.fit) ));
		list.add( new ListViewItem(R.drawable.insight, "�C���T�C�g", BitmapFactory.decodeResource(getResources(), R.drawable.insight) ));
		*/

		ListViewItemAdapter adapter = new ListViewItemAdapter(this, 0, list);

		ListView listView = (ListView)findViewById(R.id.listView);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView listView = (ListView)parent;
				CarClass item = (CarClass)listView.getItemAtPosition(position);

				Intent intent = new Intent(ListViewActivity.this, ImageViewActivity.class);
				intent.putExtra( "Car_item", item );

				startActivity(intent);
			}
		});
	}

}
