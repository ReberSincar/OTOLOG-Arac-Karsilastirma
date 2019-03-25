package com.rebersincar.otologg.Models;

public class MarkaPojo{
	private boolean tf;
	private String ad;
	private String logo;
	private int id;

	public boolean isTf(){
		return tf;
	}

	public String getAd(){
		return ad;
	}

	public String getLogo(){
		return logo;
	}

	public int getId(){
		return id;
	}

	@Override
	public String toString(){
		return
				"MarkaPojo{" +
						"tf = '" + tf + '\'' +
						",ad = '" + ad + '\'' +
						",logo = '" + logo + '\'' +
						",id = '" + id + '\'' +
						"}";
	}
}
