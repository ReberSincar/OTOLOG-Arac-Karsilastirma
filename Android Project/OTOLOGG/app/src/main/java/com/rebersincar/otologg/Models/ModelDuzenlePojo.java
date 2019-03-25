package com.rebersincar.otologg.Models;

public class ModelDuzenlePojo{
	private boolean tf;
	private int modelid;
	private String sonuc;
	private int markaid;

	public boolean isTf(){
		return tf;
	}

	public int getModelid(){
		return modelid;
	}

	public String getSonuc(){
		return sonuc;
	}

	public int getMarkaid(){
		return markaid;
	}

	@Override
 	public String toString(){
		return 
			"ModelDuzenlePojo{" + 
			"tf = '" + tf + '\'' + 
			",modelid = '" + modelid + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			",markaid = '" + markaid + '\'' + 
			"}";
		}
}
