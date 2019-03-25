package com.rebersincar.otologg.Models;

public class PuanPojo{
	private boolean tf;
	private int puan;
	private String sonuc;

	public boolean isTf(){
		return tf;
	}

	public int getPuan(){
		return puan;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"PuanPojo{" + 
			"tf = '" + tf + '\'' + 
			",puan = '" + puan + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
