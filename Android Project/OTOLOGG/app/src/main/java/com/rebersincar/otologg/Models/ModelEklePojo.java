package com.rebersincar.otologg.Models;

public class ModelEklePojo{
	private boolean tf;
	private String modelid;
	private String sonuc;
	private String markaid;

	public boolean isTf(){
		return tf;
	}

	public String getModelid(){
		return modelid;
	}

	public String getSonuc(){
		return sonuc;
	}

	public String getMarkaid(){
		return markaid;
	}

	@Override
 	public String toString(){
		return 
			"ModelEklePojo{" + 
			"tf = '" + tf + '\'' + 
			",modelid = '" + modelid + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			",markaid = '" + markaid + '\'' + 
			"}";
		}
}
