package com.example.mayintarlasi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Zorluk extends Activity {
	Button tamamButton;
	EditText edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zorluk);
		tamamButton=(Button)findViewById(R.id.tmmbuton);
		edit=(EditText)findViewById(R.id.editText1);
		
		tamamButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent yeniOyun= new Intent(Zorluk.this, MainActivity.class);
			yeniOyun.putExtra("zorluk", Integer.parseInt(edit.getText().toString()));
			yeniOyun.putExtra("isNew", true);
			startActivity(yeniOyun);
			}
		});
	}

	

}
