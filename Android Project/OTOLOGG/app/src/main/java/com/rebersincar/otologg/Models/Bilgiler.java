package com.rebersincar.otologg.Models;

public class Bilgiler{
	private String password;
	private boolean tf;
	private String ad;
	private String mail;
	private String soyad;

	public String getPassword(){
		return password;
	}

	public boolean isTf(){
		return tf;
	}

	public String getAd(){
		return ad;
	}

	public String getMail(){
		return mail;
	}

	public String getSoyad(){
		return soyad;
	}

	@Override
 	public String toString(){
		return 
			"Bilgiler{" + 
			"password = '" + password + '\'' + 
			",tf = '" + tf + '\'' + 
			",ad = '" + ad + '\'' + 
			",mail = '" + mail + '\'' + 
			",soyad = '" + soyad + '\'' + 
			"}";
		}
}
