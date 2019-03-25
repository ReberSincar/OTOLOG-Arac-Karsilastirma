package com.rebersincar.otologg.Models;

public class VideoPojo{
	private String inceleme;
	private boolean tf;
	private String reklam;
	private String kaza;

	public String getInceleme(){
		return inceleme;
	}

	public String getKaza(){
		return kaza;
	}

	public boolean isTf(){
		return tf;
	}

	public String getReklam(){
		return reklam;
	}

	@Override
 	public String toString(){
		return 
			"VideoPojo{" + 
			"inceleme = '" + inceleme + '\'' + 
			",tf = '" + tf + '\'' +
			",reklam = '" + reklam + '\'' +
					",kaza = '" + kaza + '\'' +
					"}";
		}
}
