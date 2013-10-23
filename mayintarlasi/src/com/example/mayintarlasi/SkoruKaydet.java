package com.example.mayintarlasi;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SkoruKaydet extends Activity {

	Button kaydet,atla;
	EditText kullaniciadi,kullanicisifre;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	String isim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skorukaydet);
		
		preferences=getSharedPreferences("kayit", MODE_PRIVATE);
		editor=preferences.edit();
		
		kaydet=(Button) findViewById(R.id.kaydet);
		atla=(Button) findViewById(R.id.atla);
		kullaniciadi=(EditText) findViewById(R.id.kullaniciadi);
		
		
		atla.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		kaydet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				isim=kullaniciadi.getText().toString();
				
				
				editor.putString("kisi_ad", kullaniciadi.getText().toString());
				
				editor.commit();
				
				Veritabani veritabani= new Veritabani(getApplicationContext());
				String str=""+getIntent().getStringExtra("kacakaclik");
				System.out.println(isim);
				Puan Vpuan= new Puan(0, getIntent().getIntExtra("puan", 0),
						str,isim,45);
				veritabani.addPuan(Vpuan);
				finish();
				
			}
		});
		
		
	}

	

}
