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
		
        // �X�s�i�[�̃A�C�e�����I�����ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�          
        Spinner spinner1 = (Spinner)this.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)this.findViewById(R.id.spinner2);
        
        
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){  
            //Spinner�̃h���b�v�_�E���A�C�e�����I�����ꂽ��  
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {  
                Spinner spinner = (Spinner)parent;  
                SelectedMaker = (String)spinner.getSelectedItem();  
               // Toast.makeText(SelectViewActivity.this, SelectedMaker, Toast.LENGTH_LONG).show();  
            }  
            //Spinner�̃h���b�v�_�E���A�C�e�����I������Ȃ�������  
            public void onNothingSelected(AdapterView<?> parent) {  
            }});
        
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){  
            //Spinner�̃h���b�v�_�E���A�C�e�����I�����ꂽ��  
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {  
                Spinner spinner = (Spinner)parent;  
                SelectedType = (String)spinner.getSelectedItem();  
               // Toast.makeText(SelectViewActivity.this, SelectedType, Toast.LENGTH_LONG).show();  
            }  
            //Spinner�̃h���b�v�_�E���A�C�e�����I������Ȃ�������  
            public void onNothingSelected(AdapterView<?> parent) {  
            }});  
            
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_view, menu);
		return true;
	}
	
	
	/* Search�{�^���N���b�N���ɌĂяo����郁�\�b�h */
	public void OnClick(View v) {
		Intent intent = new Intent(SelectViewActivity.this, ListViewActivity.class);
		intent.putExtra("Maker", SelectedMaker);
		intent.putExtra("Type", SelectedType);
		
		startActivity(intent);
	}

}
