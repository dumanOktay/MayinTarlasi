package com.example.mayintarlasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	LinearLayout altLayout;
	RelativeLayout ustLayout;
	ArrayList<Buton> butonList = new ArrayList<Buton>();

	int n,m, r, sayac, genislik, mayinSayi = 0, j, k,say=0,mynSay;
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

	TextView mayinTextView,sure_tv,puan_tv;
	MediaPlayer yanlis;
	
	String kaydedilecekString="",adString,sifreString;
	
	Veritabani veritb;
	
	Boolean isNew=false;

	int veriId;
	
	Veri gelenVeri;
	
	CountDownTimer timer;
	
	Kisi kullanici;
	
	int saniye=0,puan,acilanbutonsay=0,kalanmayinsayisi,artis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pre=getSharedPreferences("kayit", MODE_PRIVATE);
		
		int vol=pre.getInt("ses", 1);
		
		altLayout = (LinearLayout) findViewById(R.id.alt);
		ustLayout = (RelativeLayout) findViewById(R.id.ust);
		metrics = getMetrics();
		isNew=getIntent().getBooleanExtra("isNew", true);
		n=pre.getInt("birinciSayi", 3);
		m=pre.getInt("ikinciSayi", 3);
		mynSay=pre.getInt("mayinSayisi", 20);
		
		System.out.print("haciiiiiiiiiii "+n);
		genislik = metrics.widthPixels / n;
		System.out.println("sayiii"+n);
		yanlis = MediaPlayer.create(this, R.raw.warpyboing);
		yanlis.setVolume(vol, vol);

		bayrakButton = (Button) findViewById(R.id.btnbayrak);
		yenileButton = (Button) findViewById(R.id.yenilebtn);
		mayinTextView = (TextView) findViewById(R.id.mayin_tv);
		puan_tv=(TextView) findViewById(R.id.puan_tv);
		
		sure_tv=(TextView)findViewById(R.id.sure_tv);
		kaydet=(Button)findViewById(R.id.kaydet);
		
		veritb= new Veritabani(MainActivity.this);
		
		
		
		kullanici=veritb.getKisi(pre.getInt("kisi_id", 0));
		
		System.out.println("kullanıcı "+ kullanici.getAd());
	
		kaydet.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("bastımmm");
				
			sukriye();	
			}
		});
		
		mayinlar= new Boolean[m][n];
		
		isAcik = new Boolean[m][n];
		mayinSayilari = new int[m][n];
		mayinBilgisi= new int[m][n];
		parcalanmisDizi= new int[m][n];
		
		
		yenileButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
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
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();

	}

	public void arttir(int x, int y) {
		if (y >= 0 && x >= 0 && y < n && x < n) {
			mayinSayilari[x][y] += 1;
			isAcik[x][y] = true;
		}
	}

	public void oyun() {
		sayac(600);
		//System.out.println("aaa   "+veri.getkayit(1) );
		say=0;
		butonList.clear();
		random = new Random();
		altLayout.removeAllViews();
		for (int i = 0; i < m; i++) {//temizle
			for (int j = 0; j < n; j++) {
				mayinlar[i][j] = false;
				isAcik[i][j] = false;
				mayinBilgisi[i][j]=0;
				mayinSayilari[i][j] = 0;
				parcalanmisDizi[i][j]=0;
				mayinSayi = 0;
			}
		}

		
		if(isNew){
			do {
				mayinla();
			} while (getMayinSayisi()!=mynSay);
		}else{
			n=getIntent().getIntExtra("zorluk", 3);
			m=getIntent().getIntExtra("zorluk", 3);
			int gelen_id=getIntent().getIntExtra("veriId", 3);
			gelenVeri=veritb.getVeri(gelen_id,getIntent().getIntExtra("i", 2));
			
			kullanici=veritb.getKisi(gelenVeri.getKisi_id());
			System.out.println("user  " + kullanici.getAd());
			genislik = metrics.widthPixels / n;
			isAcik = new Boolean[n][n];
			mayinSayilari = new int[n][n];
			mayinBilgisi= new int[n][n];
			parcalanmisDizi= new int[n][n];
			parcala(gelenVeri.getKayit());
			if(getIntent().getBooleanExtra("dialog", false)){
				diyalogAc();
			}
		}

		mayinSayi=getMayinSayisi();
		
		kalanmayinsayisi=mayinSayi;
		for (int q = 0; q < m; q++) {// Mayınların etrafındaki değerleri birer
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
		mayinTextView.setText("" + mayinSayi);
		for (j = 0; j < m; j++) {

			LinearLayout yeniLayout = new LinearLayout(MainActivity.this);
			yeniLayout.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param = new LayoutParams(genislik,
					genislik);
			for (k = 0; k < n; k++) {
				final Buton yeniButon = new Buton();
				Button button = new Button(MainActivity.this);
				yeniButon.setButton(button);
				
				yeniButon.setMayinSayi(mayinSayilari[j][k]);
				if(isNew){
					yeniButon.getButton().setBackgroundResource(R.drawable.mavi);
				}
				else{
					switch (parcalanmisDizi[j][k]) {
					case 0:
						yeniButon.getButton().setBackgroundResource(R.drawable.mavi);
						break;
						

					case 2:
						yeniButon.getButton().setBackgroundResource(R.drawable.bayrak);
						break;
						
					case 4:
						yeniButon.getButton().setBackgroundResource(R.drawable.mavi);
						break;
					case 1:
						yeniButon.getButton().setBackgroundResource(R.drawable.yesil);
						if(yeniButon.getMayinSayi()>0){
							yeniButon.getButton().setText(""+yeniButon.getMayinSayi());
						}
						break;

					case 3:
						yeniButon.getButton().setBackgroundResource(R.drawable.bayrak);
						break;

					default:
						break;
					}
				}
				if (mayinlar[j][k]) {
					yeniButon.setIsMayin(true);

				} else {
					yeniButon.setIsMayin(false);

				}
				
				sayac++;
				yeniLayout.addView(yeniButon.getButton(), param);
				
				butonList.add(yeniButon);
				if(yeniButon.IsMayin()){
					yeniButon.setMayinInfo(4);
				}
				yeniButon.getButton().setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								yeniButon.setIsTik(true);
								yeniButon.getButton().setClickable(false);
								
								artis=+(acilanbutonsay*100-saniye*5+(mayinSayi-kalanmayinsayisi)*50);
								if(artis<0)
								{
									artis=5;
								}
								puan+=artis;
								puan_tv.setText(" "+puan);
								
								if (yeniButon.IsMayin()) {
									
									if (isBayrakClikked) {
										yeniButon.setMayinInfo(3);
										yeniButon.getButton().setBackgroundResource(R.drawable.bayrak);
										mayinSayi--;
										kalanmayinsayisi--;
										denetle();
									} else {
										yeniButon.getButton().setBackgroundResource(R.drawable.kirmizi);
										yanlis.start();
										kalanmayinsayisi=0;
										Toast.makeText(getApplicationContext(), "Mayına Bastınız :(  puanınız "+puan, Toast.LENGTH_LONG).show();
										goster();
										denetle();
										
									}
								} else {
									if (isBayrakClikked) {
										yeniButon.setMayinInfo(2);
										yeniButon.getButton().setBackgroundResource(R.drawable.bayrak);
									} else {
										yeniButon.setMayinInfo(1);
										yeniButon.getButton().setBackgroundResource(R.drawable.yesil);
										yeniButon.getButton().setText("" + yeniButon.getMayinSayi());
										
										acilanbutonsay++;
										butonac(1);
										
										
									}
								}
								mayinTextView.setText("" + mayinSayi);
								//System.out.println("maaaa   "+mayinBilgisi);
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
		if (i < m* n && i > 0  && !butonList.get(i).IsMayin()) {
			if (butonList.get(i).getMayinSayi() > 0) {
				butonList.get(i).getButton().setText("" + butonList.get(i).getMayinSayi());
				butonList.get(i).getButton().setBackgroundResource(R.drawable.yesil);
				butonList.get(i).setMayinInfo(1);
			}
			setTrue(i);
		}
	}

	public void butonac(int x) {

		if (x < m * n - 1 && x >= 0) {
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
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(mayinlar[i][j]){
					s++;
				}
			}
		}
		System.out.println(" mayin sayisi =" +s);
		return s;
	}
	
	public void sukriye(){
		int k=0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				//System.out.print(""+butonList.get(k).getMayinInfo());
				kaydedilecekString+=butonList.get(k).getMayinInfo()+",";
				
				k++;
			}
		}
		Veri veri= new Veri(0, saniye, kullanici.getKisi_id(), puan, n,kaydedilecekString);
		veritb.addkayit(veri);
		kaydedilecekString="";
		System.out.println(veritb.getVeri(1,1).getKayit().toString());
	}
	
	public void parcala(String str){
		String[] liste=new String[n*n];
		int q=0;
		liste=str.split(",");
		for(int i=0;i<m;i++){
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
		for(int i=0;i<m;i++){
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
	
	public void denetle(){
		if(kalanmayinsayisi==0){
			Intent kayit=new Intent(MainActivity.this,SkoruKaydet.class);
			kayit.putExtra("puan", puan);
			String str=""+n+" X"+n;
			kayit.putExtra("kacakaclik", str);
			startActivity(kayit);
			Toast.makeText(getApplicationContext(), "Kazandınız puanınız "+puan, Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
	public void sayac(int i){
			if(timer != null){
				timer.cancel();
			}
			sayac=0;
			timer= new CountDownTimer(i*1000,1000) {
				
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				sure_tv.setText(" "+saniye);
				saniye++;
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hacıı oyun Bitti", Toast.LENGTH_LONG).show();
				finish();
			}
		}.start();
	}

	
	public void mayinla(){
		
		for (int i = 0; i < m; i++) {//temizle
			for (int j = 0; j < n; j++) {
				mayinlar[i][j] = false;
				isAcik[i][j] = false;
				mayinBilgisi[i][j]=0;
				mayinSayilari[i][j] = 0;
				parcalanmisDizi[i][j]=0;
				mayinSayi = 0;
			}
		}
		
		List<Integer> rxx= new ArrayList<Integer>();
		List<Integer> ryy= new ArrayList<Integer>();
		int  rx=1, ry=1;
		System.out.println("ismaaaaaaaaaaaiiiiiiiiiiiiiiiiiiiiiiiiil" );
		for(int i=0;i<mynSay;i++){
			rx = random.nextInt(m);
			ry = random.nextInt(n);
			
			mayinlar[rx][ry] = true;
		}
	}
	
	public void diyalogAc(){
		final Dialog dialog= new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.diyalog_isim);
		dialog.setTitle("Oyunu açmak için lütfen kullanıcı adınızı ve şifrenizi girin");
		dialog.show();
		final EditText ad=(EditText) dialog.findViewById(R.id.diyalog_ad);
		final EditText sifre=(EditText) dialog.findViewById(R.id.diyalog_sifre);
		ad.setText(kullanici.getAd());
		Button button= (Button) dialog.findViewById(R.id.diyalogtamambuton);
		
	
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if(kullanici.getPassword().equals(sifre.getText().toString())){
					dialog.dismiss();
				}else{
					Toast.makeText(MainActivity.this, "Kullanıcı adı ya da şifre yanlış", Toast.LENGTH_SHORT).show();
					finish();
				}
			
				
			}
		});
		
	}

}
