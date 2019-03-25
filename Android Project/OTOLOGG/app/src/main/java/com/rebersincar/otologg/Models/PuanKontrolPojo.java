package com.rebersincar.otologg.Models;

public class PuanKontrolPojo{
	private boolean tf;
	private int puan;

	public boolean isTf(){
		return tf;
	}

	public int getPuan(){
		return puan;
	}

	@Override
 	public String toString(){
		return 
			"PuanKontrolPojo{" + 
			"tf = '" + tf + '\'' + 
			",puan = '" + puan + '\'' + 
			"}";
		}
}
