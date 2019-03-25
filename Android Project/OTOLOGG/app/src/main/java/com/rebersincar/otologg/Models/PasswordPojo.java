package com.rebersincar.otologg.Models;

public class PasswordPojo{
	private String password;
	private boolean tf;

	public String getPassword(){
		return password;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"PasswordPojo{" + 
			"password = '" + password + '\'' + 
			",tf = '" + tf + '\'' + 
			"}";
		}
}
