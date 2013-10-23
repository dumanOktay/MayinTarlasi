package com.example.mayintarlasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class Kendioyun extends Activity {

	LinearLayout altLayout;
	RelativeLayout ustLayout;
	ArrayList<Buton> butonList = new ArrayList<Buton>();

	int n, r, sayac, genislik, mayinSayi = 0, j, k,say=0;
	List<Integer> randomSayilar = new ArrayList<Integer>();

	Random random;
	
	SharedPreferences pre;
	DisplayMetrics metrics;

	Boolean mayinlar[][];
	Boolean isAcik[][];
	int mayinSayilari[][];
	int mayinBilgisi[][];
	int parcalanmisDizi[][];

	Button bayrakButton, yenileButton,kaydet;

	Boolean isBayrakClikked = false;

	TextView mayinTextView;
	MediaPlayer yanlis;
	
	String kaydedilecekString="";
	
	Veritabani veritb;
	
	Boolean isNew=false;

	int veriId;
	
	Kisi kullanici;
	
	Veri gelenVeri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kendioyun);
		
		pre=getSharedPreferences("kayit", MODE_PRIVATE);
		
		int vol=pre.getInt("ses", 1);
		
		altLayout = (LinearLayout) findViewById(R.id.y_alt);
		ustLayout = (RelativeLayout) findViewById(R.id.y_ust);
		metrics = getMetrics();
		isNew=getIntent().getBooleanExtra("isNew", true);
		n=getIntent().getIntExtra("zorluk", 3);
		genislik = metrics.widthPixels / n;
		System.out.println("sayiii"+n);
		yanlis = MediaPlayer.create(this, R.raw.warpyboing);
		yanlis.setVolume(vol, vol);

		bayrakButton = (Button) findViewById(R.id.y_btnbayrak);
		yenileButton = (Button) findViewById(R.id.y_yenilebtn);
		mayinTextView = (TextView) findViewById(R.id.y_mayinsayi);
		kaydet=(Button)findViewById(R.id.y_kaydet);
		
		veritb= new Veritabani(Kendioyun.this);
		
		kullanici=veritb.getKisi(pre.getInt("kisi_id", 0));
		
		kaydet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				System.out.println("bastımmm");
			sukriye();	
			}
		});
		
		mayinlar= new Boolean[n][n];
		
		isAcik = new Boolean[n][n];
		mayinSayilari = new int[n][n];
		mayinBilgisi= new int[n][n];
		parcalanmisDizi= new int[n][n];
		
		
		yenileButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				oyun();
				isNew=true;
				}
		});
		
		

		bayrakButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isBayrakClikked) {
					isBayrakClikked = false;
					bayrakButton.setBackgroundResource(R.drawable.bayrak3);
				} else {
					isBayrakClikked = true;
					bayrakButton.setBackgroundResource(R.drawable.bayrak2);
				}
			}
		});
		oyun();
		
	}

	public void arttir(int x, int y) {
		if (y >= 0 && x >= 0 && y < n && x < n) {
			mayinSayilari[x][y] += 1;
			isAcik[x][y] = true;
		}
	}

	public void oyun() {
		
		//System.out.println("aaa   "+veri.getkayit(1) );
		say=0;
		butonList.clear();
		random = new Random();
		altLayout.removeAllViews();
		for (int i = 0; i < n; i++) {//temizle
			for (int j = 0; j < n; j++) {
				mayinlar[i][j] = false;
				isAcik[i][j] = false;
				mayinBilgisi[i][j]=0;
				mayinSayilari[i][j] = 0;
				parcalanmisDizi[i][j]=0;
				mayinSayi = 0;
			}
		}

		int x, y, rx, ry;
		/*if(isNew){
			for (x = 0; x < n; x++) {//mayınları yerleştir
				for (y = 0; y < n; y += n/1) {
					rx = random.nextInt(n);
					ry = random.nextInt(n);
					mayinlar[rx][ry] = true;
					
				}
			}
		}else{
			int gelen_id=getIntent().getIntExtra("veriId", 5);
			gelenVeri=veritb.getVeri(gelen_id);
			System.out.println("Gelen veriiiiii   "+ gelenVeri.getKayit()+"    "+gelenVeri.getId());
			parcala(gelenVeri.getKayit());
		}

		mayinSayi=getMayinSayisi();
		//sukriye();
		
		for (int q = 0; q < n; q++) {// Mayınların etrafındaki değerleri birer
										// arttır
			for (int j = 0; j < n; j++) {
				if (mayinlar[q][j]) {
					arttir(q - 1, j - 1);
					arttir(q - 1, j);
					arttir(q - 1, j + 1);
					arttir(q, j - 1);
					arttir(q, j + 1);
					arttir(q + 1, j - 1);
					arttir(q + 1, j);
					arttir(q + 1, j + 1);

				}
			}
		}
		mayinTextView.setText("" + mayinSayi);*/
		for (j = 0; j < n; j++) {

			LinearLayout yeniLayout = new LinearLayout(Kendioyun.this);
			yeniLayout.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param = new LayoutParams(genislik,
					genislik);
			for (k = 0; k < n; k++) {
				final Buton yeniButon = new Buton();
				Button button = new Button(Kendioyun.this);
				yeniButon.setButton(button);
				
				sayac++;
				yeniLayout.addView(yeniButon.getButton(), param);
				
				butonList.add(yeniButon);
				
				yeniButon.getButton().setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								yeniButon.setIsTik(true);
									if (isBayrakClikked) {
										yeniButon.setMayinInfo(4);
										yeniButon.getButton().setBackgroundResource(R.drawable.bayrak);
										mayinSayi--;
									} else {
										Toast.makeText(getApplicationContext(), "Arkadşım Mayın Koayacaksan Bayrağı İşaretle",
												Toast.LENGTH_SHORT).show();
										yanlis.start();
										
									}
								
								
							}
						});
			}
			altLayout.addView(yeniLayout);
		}
		
	}
	
	public void setTrue(int i) {
		butonList.get(i).setIsTik(true);
	}

	public void ac(int i) {
		if (i < n * n && i > 0  && !butonList.get(i).IsMayin()) {
			if (butonList.get(i).getMayinSayi() > 0) {
				butonList.get(i).getButton().setText("" + butonList.get(i).getMayinSayi());
				butonList.get(i).getButton().setBackgroundResource(R.drawable.yesil);
				butonList.get(i).setMayinInfo(1);
			}
			setTrue(i);
		}
	}

	public void butonac(int x) {

		if (x < n * n - 1 && x >= 0) {
			if (butonList.get(x).IsTik()&& butonList.get(x).getMayinSayi() == 0 && !butonList.get(x).IsMayin()) {
				butonList.get(x).getButton().setBackgroundResource(R.drawable.yesil);
				butonList.get(x).setMayinInfo(1);
				if(butonList.get(x).getMayinSayi()==0){
					ac(x + 1);
					ac(x + n);
					ac(x + n + 1);
					ac(x + n-1);
					ac(x - 1);
					ac(x - n);
					ac(x - n+1);
					ac(x - n-1);
				}
			}
			if(say<n){
				say++;
				butonac(1);
			}
				butonac(x+1);
		}

	}
	
	public void butonac2(int x) {

		if (x < n * n - 1 && x >= 0) {
			if (butonList.get(x).getMayinSayi() == 0 && butonList.get(x).IsTik()) {
				butonList.get(x).getButton().setBackgroundResource(R.drawable.yesil);
				ac(x + 1);
				ac(x + n);
				ac(x + n + 1);
				ac(x + n-1);
				ac(x - 1);
				ac(x - n);
				ac(x - n+1);
				ac(x - n-1);
				
			}
				butonac2(x-1);
				//butonac2(x+1);
		}

	}

	public void goster() {

		for (Buton buton : butonList) {
			if (buton.IsMayin()) {
				buton.getButton().setBackgroundResource(R.drawable.kirmizi);

			} else {
				buton.getButton().setBackgroundResource(R.drawable.yesil);
			}
		}
	}

	public DisplayMetrics getMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}
	
	public int getMayinSayisi(){
		int s=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(mayinlar[i][j]){
					s++;
				}
			}
		}
		return s;
	}
	
	public void sukriye(){
		int k=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				//System.out.print(""+butonList.get(k).getMayinInfo());
				kaydedilecekString+=butonList.get(k).getMayinInfo()+",";
				
				k++;
			}
		}
		Veri veri= new Veri(0, 2, kullanici.getKisi_id(), 88, n,kaydedilecekString);
		veritb.yeniOyun(veri);
		kaydedilecekString="";
		//System.out.println(""+veritb.getVeri(0,0).getKayit().toString());
	}
	
	public void parcala(String str){
		String[] liste=new String[n*n];
		int q=0;
		liste=str.split(",");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				parcalanmisDizi[i][j]=Integer.parseInt(liste[q].toString());
				q++;
				System.out.print(" a  "+parcalanmisDizi[i][j]);
				if(parcalanmisDizi[i][j]>2){
					mayinlar[i][j]=true;
				}
			}
		}
		
	}
	
	public void tara(){
		int k=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				switch (parcalanmisDizi[i][j]) {
				case 1:
					butonList.get(k).getButton().setBackgroundResource(R.drawable.yesil);
					break;

				default:
					break;
				}
				k++;
			}
		}
	}
	

}
