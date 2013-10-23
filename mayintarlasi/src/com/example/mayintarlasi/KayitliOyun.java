package com.example.mayintarlasi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class KayitliOyun extends Activity {
	List<Veri> veriler=new ArrayList<Veri>();
	LinearLayout li;
	Veritabani veritabani;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kayitli_oyun);
		li=(LinearLayout)findViewById(R.id.layot);
		veritabani= new Veritabani(KayitliOyun.this);
		veriler=veritabani.getAllVeris(2);
		
		preferences=getSharedPreferences("kayit", MODE_PRIVATE);
		editor=preferences.edit();
		
		for(final Veri veri:veriler){
			Button button= new Button(KayitliOyun.this);
			button.setText(""+veri.getId());
			li.addView(button);
			
			button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				Intent yeniOyun= new Intent(KayitliOyun.this, MainActivity.class);
				yeniOyun.putExtra("isNew", false);
				yeniOyun.putExtra("veriId", veri.getId());
				yeniOyun.putExtra("zorluk", veri.getN());
				yeniOyun.putExtra("i", 2);
				editor.putInt("birinciSayi", 20);
				editor.putInt("ikinciSayi",20);
				editor.putInt("mayinSayisi", 10);
				editor.commit();
				startActivity(yeniOyun);
				}
			});
		}
	}

	
}
