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

public class Oyundevam extends Activity {
	List<Veri> veriler=new ArrayList<Veri>();
	LinearLayout li;
	Veritabani veritabani;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	int n;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oyundevam);
		li=(LinearLayout)findViewById(R.id.k_layot);
		veritabani= new Veritabani(Oyundevam.this);
		veriler=veritabani.getAllVeris(1);
		
		preferences=getSharedPreferences("kayit", MODE_PRIVATE);
		editor=preferences.edit();
		
		n=preferences.getInt("birinciSayi", 3);
		
		for(final Veri veri:veriler){
			Button button= new Button(Oyundevam.this);
			button.setText(""+veri.getId());
			li.addView(button);
			
			button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				Intent yeniOyun= new Intent(Oyundevam.this, MainActivity.class);
				yeniOyun.putExtra("isNew", false);
				yeniOyun.putExtra("dialog", true);
				yeniOyun.putExtra("veriId", veri.getId());
				yeniOyun.putExtra("zorluk", veri.getN());
				yeniOyun.putExtra("i", 1);
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
