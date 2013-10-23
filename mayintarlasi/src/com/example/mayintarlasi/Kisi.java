package com.example.mayintarlasi;

public class Kisi {
	int kisi_id;
	String ad;
	String password;
	int toplamPuan;
	int puan;
	
	
	public Kisi(int kisi_id, String ad, String password, int toplamPuan) {
		super();
		this.kisi_id = kisi_id;
		this.ad = ad;
		this.password = password;
		this.toplamPuan = toplamPuan;
	}
	
	public int getPuan() {
		return puan;
	}
	
	public void setPuan(int puan) {
		this.puan = puan;
	}
	public int getKisi_id() {
		return kisi_id;
	}
	public void setKisi_id(int kisi_id) {
		this.kisi_id = kisi_id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getToplamPuan() {
		return toplamPuan;
	}
	public void setToplamPuan(int toplamPuan) {
		this.toplamPuan = toplamPuan;
	}
	
	
}
