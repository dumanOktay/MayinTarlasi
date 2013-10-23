package com.example.mayintarlasi;

public class Puan {

	
	int id;
	int puan;
	String kacakaclik;
	String kisi_ad;
	int kisi_id;
	
	public Puan(int id, int puan, String kacakaclik, String kisi_ad, int kisi_id) {
		super();
		this.id = id;
		this.puan = puan;
		this.kacakaclik = kacakaclik;
		this.kisi_ad = kisi_ad;
		this.kisi_id = kisi_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuan() {
		return puan;
	}

	public void setPuan(int puan) {
		this.puan = puan;
	}

	public String getKacakaclik() {
		return kacakaclik;
	}

	public void setKacakaclik(String kacakaclik) {
		this.kacakaclik = kacakaclik;
	}

	public String getKisi_ad() {
		return kisi_ad;
	}

	public void setKisi_ad(String kisi_ad) {
		this.kisi_ad = kisi_ad;
	}

	public int getKisi_id() {
		return kisi_id;
	}

	public void setKisi_id(int kisi_id) {
		this.kisi_id = kisi_id;
	}
	
	
}
