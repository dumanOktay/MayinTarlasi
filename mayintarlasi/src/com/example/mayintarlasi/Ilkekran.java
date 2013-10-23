package com.example.mayintarlasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ilkekran extends Activity {
	Button button,kullaniciButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ilk_ekran);
		
		
		button=(Button)findViewById(R.id.btnoyun);
		kullaniciButton=(Button) findViewById(R.id.kullanicilar);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent oyun= new Intent(Ilkekran.this,Ikinciekran.class);
			startActivity(oyun);
			}
		});
		
		kullaniciButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent kullanicilar= new Intent(Ilkekran.this,Kullanicilar.class);
			startActivity(kullanicilar);
			}
		});
	}
}
