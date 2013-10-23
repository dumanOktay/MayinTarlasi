package com.example.mayintarlasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ikinciekran extends Activity{
	
	Button yeniOyunButton,kytliButton,cikisButton,oynDvm,kendiOyn,secenek,yardim,istatikButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ikinciekran);
		
		yeniOyunButton=(Button)findViewById(R.id.yenioyunbtn);
		kytliButton=(Button)findViewById(R.id.kayitlioyun);
		cikisButton=(Button)findViewById(R.id.cikisbuton);
		oynDvm=(Button)findViewById(R.id.oyundevam);
		kendiOyn=(Button)findViewById(R.id.kendioyun);
		secenek=(Button) findViewById(R.id.secenek);
		yardim=(Button) findViewById(R.id.yardim);
		istatikButton=(Button) findViewById(R.id.istatik);
		
		yeniOyunButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent yenioyun=new Intent(Ikinciekran.this, MainActivity.class);
			yenioyun.putExtra("isNew", true);
			startActivity(yenioyun);
			}
		});
		
		istatikButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent istatik= new Intent(Ikinciekran.this,Puanlist.class);
			startActivity(istatik);
			}
		});
		
		kendiOyn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent yenioyun=new Intent(Ikinciekran.this, Zorluk2.class);
			
			startActivity(yenioyun);
			}
		});
		
		oynDvm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent oyundevam= new Intent(Ikinciekran.this,Oyundevam.class);
			startActivity(oyundevam);
			}
		});
		
		kytliButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent kytliOyun= new Intent(Ikinciekran.this, KayitliOyun.class);
				startActivity(kytliOyun);
			}
		});
		secenek.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent secenek= new Intent(Ikinciekran.this,Secenek.class);
				startActivity(secenek);
				
			}
		});
		
		cikisButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
		
		yardim.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent yardim=new Intent(Ikinciekran.this, Yardim.class);
				startActivity(yardim);
				
			}
		});
	}
	
}
