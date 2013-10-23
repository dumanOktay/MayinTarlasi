package com.example.mayintarlasi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Oyun_Kaydet extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oyun__kaydet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.oyun__kaydet, menu);
		return true;
	}

}
