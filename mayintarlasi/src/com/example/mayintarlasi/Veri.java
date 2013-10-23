package com.example.mayintarlasi;

public class Veri {
	int id;
	int zaman;
	int kisi_id;
	int puan;
	int n;
	Buton buton;
	String kayit;
	
	
	
	
	
	public Veri(int id, int zaman, int kisi_id, int puan,int n, String kayit) {
		super();
		this.n=n;
		this.id = id;
		this.zaman = zaman;
		this.kisi_id = kisi_id;
		this.puan = puan;
		this.kayit = kayit;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getZaman() {
		return zaman;
	}
	public void setZaman(int zaman) {
		this.zaman = zaman;
	}
	public int getKisi_id() {
		return kisi_id;
	}
	public void setKisi_id(int kisi_id) {
		this.kisi_id = kisi_id;
	}
	public int getPuan() {
		return puan;
	}
	public void setPuan(int puan) {
		this.puan = puan;
	}
	public Buton getButon() {
		return buton;
	}
	public void setButon(Buton buton) {
		this.buton = buton;
	}
	public String getKayit() {
		return kayit;
	}
	public void setKayit(String kayit) {
		this.kayit = kayit;
	}
	
	
}
