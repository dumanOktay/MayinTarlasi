package com.example.mayintarlasi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Kullanicilar extends Activity {
	Button yeniKullaniciButton;
	
	List<Kisi> kisiList;
	
	Veritabani veri;
	
	LinearLayout liLayout;
	
	String isim;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	int kullanici_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kullanicilar);
		
		veri=new Veritabani(Kullanicilar.this);
		
		kisiList=veri.getAllKisiList();
		
		liLayout=(LinearLayout) findViewById(R.id.kullanicilayout);
		
		preferences=getSharedPreferences("kayit", MODE_PRIVATE);
		editor=preferences.edit();
		
		kullanici_id=preferences.getInt("kisi_id", 0);
		
		yeniKullaniciButton=(Button) findViewById(R.id.yenikullaniciekle);
		yeniKullaniciButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent yeniKullanici= new Intent(Kullanicilar.this,Yeni_Kullanici.class);
				startActivity(yeniKullanici);
			}

		});
		
		for(final Kisi k: kisiList){
			
			TextView tv= new TextView(Kullanicilar.this);
			tv.setGravity(Gravity.CENTER);
			tv.setTextSize(20);
			tv.setText(k.getAd());
			if(kullanici_id==k.getKisi_id()){
				tv.setTextColor(Color.GREEN);
			}
			tv.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					System.out.println("aaaa"+ k.getAd());
					
					editor.putInt("kisi_id", k.getKisi_id());
					editor.commit();
				}
			});
			liLayout.addView(tv);
		}
	}

	

}
