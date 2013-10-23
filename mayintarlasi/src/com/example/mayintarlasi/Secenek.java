package com.example.mayintarlasi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Secenek extends Activity {
	RadioButton r1,r2,r3;
	
	CheckBox c1,c2,c3;
	
	Button tmmButton;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	EditText edittext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secenek);
		
		r1=(RadioButton)findViewById(R.id.radioButton1);
		r2=(RadioButton)findViewById(R.id.radioButton2);
		r3=(RadioButton)findViewById(R.id.radioButton3);
		
		c1=(CheckBox) findViewById(R.id.checkBox_anim);
		c2=(CheckBox) findViewById(R.id.checkBox_ses);
		c3=(CheckBox) findViewById(R.id.checkBox1);
		
		preferences=getSharedPreferences("kayit", MODE_PRIVATE);
		editor=preferences.edit();
		
		
		
		tmmButton=(Button) findViewById(R.id.tamamhacibtn);
		edittext=(EditText) findViewById(R.id.edit_zorluk);
		
		if(preferences.getInt("ses", 1)==1){
			c2.setChecked(true);
		}else{
			c2.setChecked(false);
		}
		
		if(preferences.getInt("animo", 1)==1){
			c1.setChecked(true);
		}else{
			c1.setChecked(false);
		}
	
		if(preferences.getInt("baska", 1)==1){
			c3.setChecked(true);
		}else{
			c3.setChecked(false);
		}
		
		tmmButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edittext.getText().toString().length()<1){
					if(r1.isChecked()){
						editor.putInt("birinciSayi", 9);
						editor.putInt("ikinciSayi", 9);
						editor.putInt("mayinSayisi", 10);
						editor.commit();
						System.out.println("birici isaretlendu");
					}
					if(r2.isChecked()){
						editor.putInt("birinciSayi", 16);
						editor.putInt("ikinciSayi", 16);
						editor.putInt("mayinSayisi", 40);
					}
					if(r3.isChecked()){
						editor.putInt("birinciSayi", 16);
						editor.putInt("ikinciSayi", 30);
						editor.putInt("mayinSayisi", 99);
					}
				}
				else{
					int z=Integer.parseInt(edittext.getText().toString());
					editor.putInt("birinciSayi", z);
					editor.putInt("ikinciSayi", z);
					editor.putInt("mayinSayisi", 2*z);
				}
				
				
				if(c2.isChecked()){
					editor.putInt("ses", 1);
				}else{
					editor.putInt("ses", 0);
				}
				
				if(c1.isChecked()){
					editor.putInt("animo", 1);
				}else{
					editor.putInt("animo", 0);
				}
				
				if(c3.isChecked()){
					editor.putInt("baska", 1);
				}else{
					editor.putInt("baska", 0);
				}
				
				
				editor.commit();
				Intent yenioyun=new Intent(Secenek.this, MainActivity.class);
				yenioyun.putExtra("isNew", true);
				startActivity(yenioyun);
				finish();
			}
		});
	}
	

	
}
