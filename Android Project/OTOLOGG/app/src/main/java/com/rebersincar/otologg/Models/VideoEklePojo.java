package com.rebersincar.otologg.Models;

public class VideoEklePojo{
	private boolean tf;
	private String sonuc;

	public boolean isTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"VideoEklePojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
