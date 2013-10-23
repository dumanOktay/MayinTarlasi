package com.example.mayintarlasi;

import android.widget.Button;

public class Buton {
	
	Boolean isMayin;
	Boolean isTik=false;
	int mayinSayi;
	int mayinInfo=0;
	Button button;
	
	
	public int getMayinInfo() {
		return mayinInfo;
	}
	
	public void setMayinInfo(int mayinInfo) {
		this.mayinInfo = mayinInfo;
	}
	public Button getButton() {
		return button;
	}
	
	public void setButton(Button button) {
		this.button = button;
	}
	
	public Boolean IsMayin() {
		return isMayin;
	}
	
	public void setIsMayin(Boolean isMayin) {
		this.isMayin = isMayin;
	}
	
	public Boolean IsTik() {
		return isTik;
	}
	
	public void setIsTik(Boolean isTik) {
		this.isTik = isTik;
	}
	
	
	public int getMayinSayi() {
		return mayinSayi;
	}
	
	public void setMayinSayi(int mayinSayi) {
		this.mayinSayi = mayinSayi;
	}

}
