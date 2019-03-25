package com.rebersincar.otologg.Models;

public class SiralamaPojo{
	private String markaad;
	private String modelid;
	private String puan;
	private String logo;
	private String markaid;
	private String modelad;

	public String getMarkaad(){
		return markaad;
	}

	public String getModelid(){
		return modelid;
	}

	public String getPuan(){
		return puan;
	}

	public String getLogo(){
		return logo;
	}

	public String getMarkaid(){
		return markaid;
	}

	public String getModelad(){
		return modelad;
	}

	@Override
 	public String toString(){
		return 
			"SiralamaPojo{" + 
			"markaad = '" + markaad + '\'' + 
			",modelid = '" + modelid + '\'' + 
			",puan = '" + puan + '\'' + 
			",logo = '" + logo + '\'' + 
			",markaid = '" + markaid + '\'' + 
			",modelad = '" + modelad + '\'' + 
			"}";
		}
}
