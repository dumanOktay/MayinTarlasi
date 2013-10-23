package com.example.mayintarlasi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper{
	
	
	private static final int DATABASE_VERSION=1;
	
	private static final String DATABASE_NAME="abc1.sqlite";
	
	private static final String TABLO_AD="veri";
	
	private static final String TABLO_YENI_OYUN="kayitlioyun";

	private static final String KEY_ID="id";
	private static final String KEY_N="n";
	private static final String KEY_KISI_ID="kisi_id";
	private static final String KEY_ZAMAN="zaman";
	private static final String KEY_PUAN="puan";
	private static final String KEY_KAYIT="kayit";
	
	
	
	public Veritabani(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlkomutu="CREATE  TABLE "+TABLO_AD+" ("+KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "
				+KEY_KISI_ID+" INTEGER, "+KEY_ZAMAN+" INTEGER, "+KEY_PUAN+" INTEGER, "+KEY_N+"  INTEGER, "+KEY_KAYIT+" VARCHAR)";
		String sqlkomutu2="CREATE  TABLE "+TABLO_YENI_OYUN+" ("+KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "
				+KEY_KISI_ID+" INTEGER, "+KEY_ZAMAN+" INTEGER, "+KEY_PUAN+" INTEGER, "+KEY_N+"  INTEGER, "+KEY_KAYIT+" VARCHAR)";
		db.execSQL(sqlkomutu2);
		System.out.println("eliffff");
		db.execSQL(sqlkomutu);
		
		String sqlkomutu3="CREATE  TABLE kisi (\"kisi_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
				"\"ad\" VARCHAR, \"sifre\" VARCHAR, \"puan\" INTEGER, \"top_puan\" INTEGER)";
		db.execSQL(sqlkomutu3);
		
		String sqlkomutu4= "CREATE  TABLE puan ( \"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ,  \"puan\" INTEGER,  \"kisi_id\" INTEGER," +
				"  \"k_ad\" TEXT,  \"kacakaclik\" VARCHAR) ";
		db.execSQL(sqlkomutu4);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLO_AD);

		// Create tables again
		onCreate(db);
	}
	
	public void addkayit(Veri veri){
		SQLiteDatabase db= this.getWritableDatabase();
		
		ContentValues val= new ContentValues();
		val.put(KEY_KAYIT,veri.getKayit());
		val.put(KEY_PUAN, veri.getPuan());
		val.put(KEY_KISI_ID, veri.getKisi_id());
		val.put(KEY_ZAMAN, veri.getZaman());
		val.put(KEY_N, veri.getN());
		db.insert(TABLO_AD, null, val);
		db.close();
	}
	String getkayit(int id){
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=db.query(TABLO_AD, new String[]{"id", "kayit"}, KEY_ID+"=?", new String[]{String.valueOf(id)}, null,
				null, null, null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		String str=cursor.getString(1);
		return str;
	}
	
	public Veri getVeri(int id,int i){
		SQLiteDatabase veriDatabase=this.getReadableDatabase();
		String sqlkomut=TABLO_AD;
		if(i==2){
			sqlkomut=TABLO_YENI_OYUN;
		}
		Cursor cursor=veriDatabase.query(sqlkomut, new String[]{KEY_ID ,KEY_KISI_ID,KEY_PUAN,KEY_N,KEY_ZAMAN,KEY_KAYIT}, KEY_ID+"=?", new String[]{String.valueOf(id)}, 
				null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
		}
		Veri veri= new Veri(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(1)),
				Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4).toString()),cursor.getString(5));
		return veri;
	}
	
	public void yeniOyun(Veri veri){
		SQLiteDatabase db= this.getWritableDatabase();
		
		ContentValues val= new ContentValues();
		val.put(KEY_KAYIT,veri.getKayit());
		val.put(KEY_PUAN, veri.getPuan());
		val.put(KEY_KISI_ID, veri.getKisi_id());
		val.put(KEY_ZAMAN, veri.getZaman());
		val.put(KEY_N, veri.getN());
		db.insert(TABLO_YENI_OYUN, null, val);
		db.close();
		
	}
	
	public void addPuan(Puan puan){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues val= new ContentValues();
		
		val.put("puan", puan.getPuan());
		val.put("k_ad", puan.getKisi_ad());
		val.put("kacakaclik", puan.getKacakaclik());
		val.put("kisi_id", puan.getKisi_id());
		db.insert("puan", null, val);
		db.close();
	}
	
	public void addKisi(Kisi kisi){
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues val=new ContentValues();
		
		val.put("sifre", kisi.getPassword());
		val.put("ad", kisi.getAd());
		val.put("top_puan", kisi.getToplamPuan());
		val.put("puan", kisi.getPuan());
		
		db.insert("kisi", null, val);
		
	}
	
	public Kisi getKisi(int id) {
	
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=db.query("kisi", new String[]{"kisi_id","ad","sifre","puan","top_puan"}
		, "kisi_id=?", new String[]{String.valueOf(id)}, null, null, null, null);
		
		if(cursor!=null){
			cursor.moveToFirst();
		}
		
		Kisi kisi= new Kisi(Integer.parseInt(cursor.getString(0)),cursor.getString(1) ,cursor.getString(2)
						,Integer.parseInt(cursor.getString(3)));
				
		return kisi;

	}
	
	public List<Kisi> getAllKisiList(){
		List<Kisi> kisiList= new ArrayList<Kisi>();
		String sqlkomutu="select * from kisi ";
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=db.rawQuery(sqlkomutu, null);
		
		if(cursor.moveToFirst()){
			do {
				Kisi kisi= new Kisi(Integer.parseInt(cursor.getString(0)),cursor.getString(1) ,cursor.getString(2)
						,Integer.parseInt(cursor.getString(4)));
				kisiList.add(kisi);
			} while (cursor.moveToNext());
		}
		return kisiList;
	}
	
	public  List<Puan> getAllPuans(){
		List<Puan> puanList= new ArrayList<Puan>();
		String sqlkomutu="select * from puan ORDER BY puan DESC";
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=db.rawQuery(sqlkomutu, null);
		
		if(cursor.moveToFirst()){
			do {
				Puan puan= new Puan(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)) ,cursor.getString(4)
						,cursor.getString(3),Integer.parseInt(cursor.getString(2)));
				puanList.add(puan);
			} while (cursor.moveToNext());
		}
		return puanList;
	}
	public List<Veri> getAllVeris(int i){
		List<Veri> veriler= new ArrayList<Veri>();
		
		String sqlKomutu="select * from "+TABLO_AD;
		
		if(i==2){
			 sqlKomutu="select * from "+TABLO_YENI_OYUN;
		}
		
		SQLiteDatabase veritabani=this.getReadableDatabase();
		Cursor cursor= veritabani.rawQuery(sqlKomutu, null);
		
		if(cursor.moveToFirst()){
			do {
				Veri veri= new Veri(Integer.parseInt(cursor.getString(0)),
						Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
						Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),cursor.getString(5));
				veriler.add(veri);
			} while (cursor.moveToNext());
		}
		return veriler;
	}
}
