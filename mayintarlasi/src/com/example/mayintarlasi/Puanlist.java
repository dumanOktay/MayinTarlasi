package com.example.mayintarlasi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Puanlist extends Activity {
	List<Puan> puanList=new ArrayList<Puan>();
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puanlist);
		layout=(LinearLayout) findViewById(R.id.listlayout);
		Veritabani vt= new Veritabani(Puanlist.this);
		puanList=vt.getAllPuans();
		
		for(Puan puan: puanList){
			TextView tv= new TextView(Puanlist.this);
			
			String str="      "+puan.getPuan()+" "+puan.getKisi_ad()+" "+puan.getKacakaclik()+"  ";
			tv.setText(str);
			layout.addView(tv);
		}
		
	}



}
