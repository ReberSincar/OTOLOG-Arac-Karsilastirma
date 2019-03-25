package com.rebersincar.otologg.Models;

public class ResimPojo{
	private String resim;
	private int resimId;

	public String getResim(){
		return resim;
	}

	public int getResimId(){
		return resimId;
	}

	@Override
 	public String toString(){
		return 
			"ResimPojo{" + 
			"resim = '" + resim + '\'' + 
			",resimId = '" + resimId + '\'' + 
			"}";
		}
}
