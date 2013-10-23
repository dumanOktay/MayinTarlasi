package com.example.mayintarlasi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Yeni_Kullanici extends Activity {
	EditText kullanic_ad_ed,kullanic_sifre_ed;
	Button kaydetButton;
	
	List<Kisi> kisiList;
	
	Veritabani veri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yeni_kullanici);
		
		veri=new Veritabani(Yeni_Kullanici.this);
		
		kisiList=veri.getAllKisiList();
		kaydetButton=(Button) findViewById(R.id.yeni_kullanici_kaydet);
		 
		kullanic_ad_ed=(EditText) findViewById(R.id.yeni_kullanici_ad);
		kullanic_sifre_ed=(EditText) findViewById(R.id.yeni_kullanici_sifre);
		
		
		
		kaydetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Kisi kisi= new Kisi(0, kullanic_ad_ed.getText().toString(), kullanic_sifre_ed.getText().toString(), 0);
			
			veri.addKisi(kisi);
			}
		});
	}
}
